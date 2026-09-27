package com.gfg.showtime.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.gfg.showtime.domain.Movie;
import com.gfg.showtime.domain.Show;
import com.gfg.showtime.domain.ShowSeat;
import com.gfg.showtime.domain.Theater;
import com.gfg.showtime.domain.TheaterSeats;
import com.gfg.showtime.exception.NotFoundException;
import com.gfg.showtime.repository.MovieRepository;
import com.gfg.showtime.repository.ShowRepository;
import com.gfg.showtime.repository.TheaterRepository;
import com.gfg.showtime.resource.ShowResource;

@Service
public class ShowService {

	private final ShowRepository showRepository;
	private final MovieRepository movieRepository;
	private final TheaterRepository theaterRepository;

	public ShowService(ShowRepository showRepository, MovieRepository movieRepository, TheaterRepository theaterRepository) {
		this.showRepository = showRepository;
		this.movieRepository = movieRepository;
		this.theaterRepository = theaterRepository;
	}

	// A show copies the theatre's seats into its own ShowSeats: the same numbers, plus a price and a booked flag
	@Transactional
	public ShowResource addShow(ShowResource request) {
		Movie movie = movieRepository.findById(request.movieId())
				.orElseThrow(() -> new NotFoundException("Movie not found: " + request.movieId()));
		Theater theater = theaterRepository.findById(request.theaterId())
				.orElseThrow(() -> new NotFoundException("Theater not found: " + request.theaterId()));

		Show show = Show.builder().showTime(request.showTime()).movie(movie).theater(theater).build();
		for (TheaterSeats seat : theater.getSeats()) {
			show.getSeats().add(ShowSeat.builder()
					.seatNumber(seat.getSeatNumber())
					.seatType(seat.getSeatType())
					.rate(seat.getSeatType().price())
					.show(show)
					.build());
		}
		return Show.toResource(showRepository.save(show));
	}

	// City is required; movie and theatre narrow it down. Blank strings count as "not given".
	@Transactional(readOnly = true)
	public List<ShowResource> searchShows(String city, String movieTitle, String theaterName) {
		return showRepository.search(city, blankToNull(movieTitle), blankToNull(theaterName)).stream()
				.map(Show::toResource).toList();
	}

	private static String blankToNull(String value) {
		return StringUtils.hasText(value) ? value : null;
	}
}
