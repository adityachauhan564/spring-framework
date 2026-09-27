package com.gfg.showtime.domain;

import java.util.ArrayList;
import java.util.List;

import com.gfg.showtime.resource.TheaterResource;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "theaters")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Theater {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String city;

	@Column(nullable = false)
	private String address;

	@OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
	@Builder.Default
	private List<Show> shows = new ArrayList<>();

	// The physical seats. Each show copies them into its own ShowSeats (with price and booked flag).
	@OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
	@Builder.Default
	private List<TheaterSeats> seats = new ArrayList<>();

	public static Theater toEntity(TheaterResource request) {
		return Theater.builder().name(request.name()).city(request.city()).address(request.address()).build();
	}

	public static TheaterResource toResource(Theater theater) {
		return new TheaterResource(theater.getId(), theater.getName(), theater.getCity(), theater.getAddress());
	}
}
