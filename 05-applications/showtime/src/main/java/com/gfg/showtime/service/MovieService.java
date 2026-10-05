package com.gfg.showtime.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gfg.showtime.domain.Movie;
import com.gfg.showtime.enums.Genre;
import com.gfg.showtime.exception.ConflictException;
import com.gfg.showtime.exception.NotFoundException;
import com.gfg.showtime.repository.MovieRepository;
import com.gfg.showtime.resource.MovieResource;

/*
 * Services return resources (DTOs), not entities, and read the data inside a transaction.
 * spring.jpa.open-in-view=false closes the database session as soon as the service returns,
 * so lazy lists (like a movie's reviews) must be read here, before that happens.
 */
@Service
@Transactional(readOnly = true)
public class MovieService {

	private final MovieRepository movieRepository;

	public MovieService(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	@Transactional
	public MovieResource addMovie(MovieResource request) {
		if (movieRepository.existsByTitle(request.title())) {
			throw new ConflictException("Movie already exists: " + request.title());
		}
		return Movie.toResource(movieRepository.save(Movie.toEntity(request)));
	}

	public MovieResource getMovie(long id) {
		return Movie.toResource(movieRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Movie not found: " + id)));
	}

	public MovieResource getMovie(String title) {
		return Movie.toResource(movieRepository.findByTitle(title)
				.orElseThrow(() -> new NotFoundException("Movie not found: " + title)));
	}

	public List<MovieResource> topRated(Genre genre) {
		return movieRepository.findTop5ByGenreAndRatingNotNullOrderByRatingDesc(genre).stream()
				.map(Movie::toResource).toList();
	}
}
