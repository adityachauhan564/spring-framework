package com.gfg.showtime.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gfg.showtime.domain.Theater;
import com.gfg.showtime.domain.TheaterSeats;
import com.gfg.showtime.enums.SeatType;
import com.gfg.showtime.exception.NotFoundException;
import com.gfg.showtime.repository.TheaterRepository;
import com.gfg.showtime.resource.TheaterResource;

@Service
public class TheaterService {

	private final TheaterRepository theaterRepository;

	public TheaterService(TheaterRepository theaterRepository) {
		this.theaterRepository = theaterRepository;
	}

	// Every theatre gets the same small layout: row 1 regular (1A-1E), row 2 recliners (2A-2E).
	// cascade = ALL on Theater.seats means saving the theatre also saves its seats.
	@Transactional
	public TheaterResource addTheater(TheaterResource request) {
		Theater theater = Theater.toEntity(request);
		for (char column = 'A'; column <= 'E'; column++) {
			addSeat(theater, "1" + column, SeatType.REGULAR);
			addSeat(theater, "2" + column, SeatType.RECLINER);
		}
		return Theater.toResource(theaterRepository.save(theater));
	}

	private void addSeat(Theater theater, String number, SeatType type) {
		theater.getSeats().add(TheaterSeats.builder().seatNumber(number).seatType(type).theater(theater).build());
	}

	@Transactional(readOnly = true)
	public TheaterResource getTheater(long id) {
		return theaterRepository.findById(id).map(Theater::toResource)
				.orElseThrow(() -> new NotFoundException("Theater not found: " + id));
	}
}
