package com.gfg.showtime.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gfg.showtime.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // JPQL uses entity and field names (Review, r.movie.id), not table names, so it survives a table rename
    @Query("select avg(r.rating) from Review r where r.movie.id = :movieId")
    Double averageRating(long movieId);
}
