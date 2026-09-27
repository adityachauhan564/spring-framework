package com.gfg.showtime.domain;

import com.gfg.showtime.enums.SeatType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "theater_seats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TheaterSeats {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "seat_number", nullable = false)
	private String seatNumber;

	@Enumerated(EnumType.STRING)
	@Column(name = "seat_type", nullable = false)
	private SeatType seatType;

	@ManyToOne
	private Theater theater;
}
