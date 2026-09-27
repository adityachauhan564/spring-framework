package com.learning.irctc.entities;

import java.util.List;

/*
 * A user as stored in users.json. There is no password field: only its BCrypt hash is kept,
 * so reading the file never reveals anyone's password.
 */
public record User(String userId, String name, String hashedPassword, List<Ticket> ticketsBooked) {
}
