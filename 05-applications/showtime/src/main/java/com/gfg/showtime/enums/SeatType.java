package com.gfg.showtime.enums;

// An enum can carry data: each seat type knows its price (the course hard-coded 100 for every seat)
public enum SeatType {
	REGULAR(150),
	RECLINER(300);

	private final int price;

	SeatType(int price) {
		this.price = price;
	}

	public int price() {
		return price;
	}
}
