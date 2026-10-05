package com.gfg.showtime.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gfg.showtime.enums.Genre;
import com.gfg.showtime.resource.MovieResource;
import com.gfg.showtime.service.MovieService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/movie")
public class MovieController {

	private final MovieService movieService;

	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}

	@PostMapping("/add")                          // ADMIN only (SecurityConfiguration)
	@ResponseStatus(HttpStatus.CREATED)
	public MovieResource addMovie(@RequestBody @Valid MovieResource movieRequest) {
		return movieService.addMovie(movieRequest);
	}

	@GetMapping("/{id}")
	public MovieResource getMovieById(@PathVariable long id) {
		return movieService.getMovie(id);
	}

	@GetMapping("/title")
	public MovieResource getMovieByTitle(@RequestParam String title) {
		return movieService.getMovie(title);
	}

	// The 5 best-rated movies of one genre:  /movie/top?genre=SCI_FI
	@GetMapping("/top")
	public List<MovieResource> topRated(@RequestParam Genre genre) {
		return movieService.topRated(genre);
	}
}
