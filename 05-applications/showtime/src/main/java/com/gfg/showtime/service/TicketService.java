package com.gfg.showtime.service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gfg.showtime.domain.Show;
import com.gfg.showtime.domain.ShowSeat;
import com.gfg.showtime.domain.Ticket;
import com.gfg.showtime.domain.User;
import com.gfg.showtime.exception.ConflictException;
import com.gfg.showtime.exception.NotFoundException;
import com.gfg.showtime.notification.BookingNotification;
import com.gfg.showtime.repository.ShowRepository;
import com.gfg.showtime.repository.TicketRepository;
import com.gfg.showtime.repository.UserRepository;
import com.gfg.showtime.resource.BookingResource;
import com.gfg.showtime.resource.TicketResource;

/*
 * Booking, the heart of the app. Three things make it correct:
 *  1. @Transactional: the ticket and every seat change are saved together, or nothing is.
 *  2. ShowSeat's @Version: if two people book the same seat at the same moment, one commit fails
 *     (ObjectOptimisticLockingFailureException -> 409) instead of both getting a ticket.
 *  3. The notification is an EVENT, handled only after the commit (see notification/): no email for a
 *     booking that was rolled back, and a mail or Kafka problem can't undo a booking.
 */
@Service
public class TicketService {

	private static final Logger log = LoggerFactory.getLogger(TicketService.class);

	private final UserRepository userRepository;
	private final ShowRepository showRepository;
	private final TicketRepository ticketRepository;
	private final ApplicationEventPublisher events;

	public TicketService(UserRepository userRepository, ShowRepository showRepository,
			TicketRepository ticketRepository, ApplicationEventPublisher events) {
		this.userRepository = userRepository;
		this.showRepository = showRepository;
		this.ticketRepository = ticketRepository;
		this.events = events;
	}

	@Transactional
	public TicketResource bookTicket(String userEmail, BookingResource booking) {
		User user = userRepository.findByEmail(userEmail).orElseThrow();
		Show show = showRepository.findById(booking.showId())
				.orElseThrow(() -> new NotFoundException("Show not found: " + booking.showId()));

		List<ShowSeat> seats = show.getSeats().stream()
				.filter(seat -> booking.seatsNumbers().contains(seat.getSeatNumber()))
				.filter(seat -> seat.getSeatType() == booking.seatType() && !seat.isBooked())
				.sorted(Comparator.comparing(ShowSeat::getSeatNumber))
				.toList();
		if (seats.size() != booking.seatsNumbers().size()) {
			throw new ConflictException("Seats " + booking.seatsNumbers() + " are not all free " + booking.seatType() + " seats");
		}

		Ticket ticket = ticketRepository.save(Ticket.builder()
				.user(user)
				.show(show)
				.amount(seats.stream().mapToInt(ShowSeat::getRate).sum())
				.allottedSeats(seats.stream().map(ShowSeat::getSeatNumber).collect(Collectors.joining(" ")))
				.build());
		Date now = new Date();
		for (ShowSeat seat : seats) {              // loaded in this transaction: the changes are saved at commit
			seat.setBooked(true);
			seat.setBookedAt(now);
			seat.setTicket(ticket);
		}
		ticket.getSeats().addAll(seats);
		log.info("Booked {} for {} (ticket {})", ticket.getAllottedSeats(), userEmail, ticket.getId());

		events.publishEvent(new BookingNotification(ticket.getId(), user.getName(), user.getEmail(), user.getMobile(),
				show.getMovie().getTitle(), show.getTheater().getName(), show.getShowTime(),
				ticket.getAllottedSeats(), ticket.getAmount()));
		return Ticket.toResource(ticket);
	}

	// A ticket is visible to its owner and to admins; anyone else gets 403
	@Transactional(readOnly = true)
	public TicketResource getTicket(long id, String userEmail, boolean isAdmin) {
		Ticket ticket = ticketRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Ticket not found: " + id));
		if (!isAdmin && !ticket.getUser().getEmail().equals(userEmail)) {
			throw new AccessDeniedException("Not your ticket");
		}
		return Ticket.toResource(ticket);
	}
}
