package com.gfg.showtime.resource;

import java.util.List;

import com.gfg.showtime.enums.Role;

// What the API returns about a user. There is no password field on purpose - not even a hashed one.
public record UserResource(long id, String name, String mobile, String email, Role role, List<TicketResource> tickets) {
}
