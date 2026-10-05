package com.gfg.showtime.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gfg.showtime.domain.Movie;
import com.gfg.showtime.enums.Genre;

public interface MovieRepository extends JpaRepository<Movie, Long> {

	boolean existsByTitle(String title);

	Optional<Movie> findByTitle(String title);

	// "Top 5 movies by genre" from design.txt, written just as a method name. Spring Data turns it into:
	// where genre = ? and rating is not null order by rating desc limit 5
	List<Movie> findTop5ByGenreAndRatingNotNullOrderByRatingDesc(Genre genre);
}
