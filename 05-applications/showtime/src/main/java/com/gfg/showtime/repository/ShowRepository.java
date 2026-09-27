package com.gfg.showtime.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gfg.showtime.domain.Show;

public interface ShowRepository extends JpaRepository<Show, Long> {

    // One query instead of three: an optional filter is "(:param is null or field = :param)".
    // (The course had three native SQL queries, one per combination.)
    @Query("""
            select s from Show s
            where lower(s.theater.city) = lower(:city)
              and (:movieTitle is null or s.movie.title = :movieTitle)
              and (:theaterName is null or s.theater.name = :theaterName)
            order by s.showTime""")
    List<Show> search(String city, String movieTitle, String theaterName);
}
