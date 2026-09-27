package com.gfg.showtime.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gfg.showtime.resource.ShowResource;
import com.gfg.showtime.service.ShowService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/show")
public class ShowController {

	private final ShowService showService;

	public ShowController(ShowService showService) {
		this.showService = showService;
	}

	//  /show/search?city=Mumbai   &movieName=Inception   &theaterName=PVR Phoenix   (the last two optional)
	@GetMapping("/search")
	public List<ShowResource> search(@RequestParam("city") String city,
			@RequestParam(name = "movieName", required = false) String movieName,
			@RequestParam(name = "theaterName", required = false) String theaterName) {
		return showService.searchShows(city, movieName, theaterName);
	}

	@PostMapping("/add")                          // ADMIN only
	@ResponseStatus(HttpStatus.CREATED)
	public ShowResource addShow(@RequestBody @Valid ShowResource showResource) {
		return showService.addShow(showResource);    // the course returned the request instead of the saved show
	}
}
