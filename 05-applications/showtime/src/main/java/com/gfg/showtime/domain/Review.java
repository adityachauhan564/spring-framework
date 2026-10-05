package com.gfg.showtime.domain;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.gfg.showtime.resource.ReviewResource;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "review_table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String movieReview;

    private double rating;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)   // even without @JoinColumn the column would be "movie_id": <field>_<id column>
    private Movie movie;

    @ManyToOne
    private User user;                                  // who wrote it: the user who is logged in

    @CreationTimestamp
    private Date createdDate;

    @UpdateTimestamp
    private Date updatedDate;

    public static ReviewResource toResource(Review review) {
        return new ReviewResource(review.getId(), review.getMovie().getId(), review.getMovieReview(), review.getRating());
    }

    public static List<ReviewResource> toResource(List<Review> reviews) {
        return reviews == null ? List.of() : reviews.stream().map(Review::toResource).toList();
    }
}
