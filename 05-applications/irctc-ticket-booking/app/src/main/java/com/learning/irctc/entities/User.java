package com.learning.irctc.entities;

import java.util.List;

/*
 * A user, as stored in users.json. There is no password field: only its BCrypt hash is kept
 * (a scrambled one-way version), so reading the file never shows anyone's password.
 */
public record User(String userId, String name, String hashedPassword, List<Ticket> ticketsBooked) {
}
