package com.gfg.showtime.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.gfg.showtime.resource.TicketResource;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tickets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "alloted_seats", nullable = false)
	private String allottedSeats;

	@Column(name = "amount", nullable = false)
	private double amount;

	@CreationTimestamp
	@Column(name = "booked_at", nullable = false)
	private Date bookedAt;

	@ManyToOne
	private User user;

	@ManyToOne
	private Show show;

	// mappedBy gives the name of the field in ShowSeat that points back HERE ("ticket").
	// The course had mappedBy = "show", which wrongly linked a ticket's seats to... ALL the show's seats.
	@OneToMany(mappedBy = "ticket")
	@Builder.Default
	private List<ShowSeat> seats = new ArrayList<>();

	public static TicketResource toResource(Ticket ticket) {
		Show show = ticket.getShow();
		return new TicketResource(ticket.getId(), ticket.getAllottedSeats(), ticket.getAmount(), ticket.getBookedAt(),
				show.getId(), show.getMovie().getTitle(), show.getTheater().getName(), show.getShowTime());
	}

	public static List<TicketResource> toResource(List<Ticket> tickets) {
		return tickets == null ? List.of() : tickets.stream().map(Ticket::toResource).toList();
	}
}
