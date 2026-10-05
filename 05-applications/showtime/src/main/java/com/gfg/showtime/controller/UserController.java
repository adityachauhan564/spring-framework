package com.gfg.showtime.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gfg.showtime.resource.SignupRequest;
import com.gfg.showtime.resource.UserResource;
import com.gfg.showtime.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/signup")                       // open to everyone
	@ResponseStatus(HttpStatus.CREATED)
	public UserResource signup(@RequestBody @Valid SignupRequest request) {
		return userService.signup(request);
	}

	// Your own profile and tickets. There is no "GET /user/{id}" for normal users: it would let
	// anyone read anyone else's details just by trying ids 1, 2, 3...
	@GetMapping("/me")
	public UserResource me(@AuthenticationPrincipal UserDetails user) {
		return userService.getByEmail(user.getUsername());
	}

	@GetMapping("/{id}")                          // ADMIN only
	public UserResource getUser(@PathVariable long id) {
		return userService.getUser(id);
	}
}
