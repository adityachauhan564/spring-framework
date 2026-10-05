package com.gfg.showtime.domain;

import java.util.ArrayList;
import java.util.List;

import com.gfg.showtime.enums.Genre;
import com.gfg.showtime.resource.MovieResource;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

/*
 * Entities use @Getter/@Setter, not @Data. @Data also generates equals/hashCode/toString over EVERY
 * field, relationships included. That loads lazy collections, and can go round in a loop forever
 * (Movie -> Show -> Movie -> ...).
 */
@Entity
@Table(name = "movies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false, unique = true)
	private String title;

	@Enumerated(EnumType.STRING)      // stored as "ACTION", not as a number (a number would change if someone reorders the enum)
	private Genre genre;

	private Double rating;            // the average of its reviews; null until the first review comes in

	@OneToMany(mappedBy = "movie")
	@Builder.Default
	private List<Review> reviews = new ArrayList<>();

	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
	@Builder.Default
	private List<Show> shows = new ArrayList<>();

	public static Movie toEntity(MovieResource request) {
		return Movie.builder().title(request.title()).genre(request.genre()).build();
	}

	public static MovieResource toResource(Movie movie) {
		return new MovieResource(movie.getId(), movie.getTitle(), movie.getGenre(), movie.getRating(),
				Review.toResource(movie.getReviews()));
	}
}
