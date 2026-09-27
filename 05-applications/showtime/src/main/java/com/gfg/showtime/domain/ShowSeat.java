package com.gfg.showtime.domain;

import java.util.Date;
import java.util.List;

import com.gfg.showtime.enums.SeatType;
import com.gfg.showtime.resource.ShowSeatsResource;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * One seat for one show: the thing people actually book.
 *
 * @Version = optimistic locking. Hibernate adds "where version = ?" to every update and increments it.
 * Two customers who both read this seat as free can both try to book it; the first update wins,
 * the second finds the version changed, updates 0 rows and fails -> TicketService answers 409.
 * Without it, the second booking would silently overwrite the first: one seat, two tickets.
 */
@Entity
@Table(name = "show_seats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShowSeat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "seat_number", nullable = false)
	private String seatNumber;

	@Column(name = "rate", nullable = false)
	private int rate;

	@Enumerated(EnumType.STRING)
	@Column(name = "seat_type", nullable = false)
	private SeatType seatType;

	@Column(name = "is_booked", nullable = false)
	private boolean booked;

	@Column(name = "booked_at")
	private Date bookedAt;                 // set when booked (the course used @CreationTimestamp: the time the seat was created)

	@Version
	private long version;

	@ManyToOne
	private Show show;

	@ManyToOne
	private Ticket ticket;                 // null while the seat is free

	public static List<ShowSeatsResource> toResource(List<ShowSeat> seats) {
		return seats == null ? List.of() : seats.stream().map(ShowSeat::toResource).toList();
	}

	public static ShowSeatsResource toResource(ShowSeat seat) {
		return new ShowSeatsResource(seat.getId(), seat.getSeatNumber(), seat.getRate(), seat.getSeatType(),
				seat.isBooked(), seat.getBookedAt());
	}
}
