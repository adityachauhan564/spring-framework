package com.gfg.showtime.resource;

import java.util.Date;

import com.gfg.showtime.enums.SeatType;

public record ShowSeatsResource(long id, String seatNumber, int rate, SeatType seatType, boolean booked, Date bookedAt) {
}
