package com.gfg.showtime.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gfg.showtime.enums.Role;
import com.gfg.showtime.resource.BookingResource;
import com.gfg.showtime.resource.TicketResource;
import com.gfg.showtime.service.TicketService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/ticket")
public class TicketController {

	private final TicketService ticketService;

	public TicketController(TicketService ticketService) {
		this.ticketService = ticketService;
	}

	// The ticket is booked for the logged-in user. The body only says which show and which seats
	@PostMapping("/book")
	@ResponseStatus(HttpStatus.CREATED)
	public TicketResource bookTicket(@RequestBody @Valid BookingResource booking, @AuthenticationPrincipal UserDetails user) {
		return ticketService.bookTicket(user.getUsername(), booking);
	}

	@GetMapping("/{id}")
	public TicketResource getTicket(@PathVariable long id, @AuthenticationPrincipal UserDetails user) {
		boolean isAdmin = user.getAuthorities().contains(new SimpleGrantedAuthority(Role.ADMIN.name()));
		return ticketService.getTicket(id, user.getUsername(), isAdmin);
	}
}
