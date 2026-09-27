package com.gfg.showtime.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gfg.showtime.resource.TheaterResource;
import com.gfg.showtime.service.TheaterService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/theater")
public class TheaterController {

	private final TheaterService theaterService;

	public TheaterController(TheaterService theaterService) {
		this.theaterService = theaterService;
	}

	@PostMapping("/add")                          // ADMIN only
	@ResponseStatus(HttpStatus.CREATED)
	public TheaterResource addTheater(@RequestBody @Valid TheaterResource theaterResource) {
		return theaterService.addTheater(theaterResource);
	}

	@GetMapping("/{id}")
	public TheaterResource getTheater(@PathVariable long id) {
		return theaterService.getTheater(id);
	}
}
