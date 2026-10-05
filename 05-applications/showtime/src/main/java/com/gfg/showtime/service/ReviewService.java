package com.gfg.showtime.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gfg.showtime.domain.Movie;
import com.gfg.showtime.domain.Review;
import com.gfg.showtime.exception.NotFoundException;
import com.gfg.showtime.repository.MovieRepository;
import com.gfg.showtime.repository.ReviewRepository;
import com.gfg.showtime.repository.UserRepository;
import com.gfg.showtime.resource.ReviewResource;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    public ReviewService(ReviewRepository reviewRepository, MovieRepository movieRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
    }

    // One transaction: the review and the movie's new average rating are saved together, or not at all
    @Transactional
    public ReviewResource addReview(ReviewResource request, String userEmail) {
        Movie movie = movieRepository.findById(request.movieId())
                .orElseThrow(() -> new NotFoundException("Movie not found: " + request.movieId()));
        Review review = reviewRepository.save(Review.builder()
                .movie(movie)
                .user(userRepository.findByEmail(userEmail).orElseThrow())
                .movieReview(request.movieReview())
                .rating(request.rating())
                .build());
        reviewRepository.flush();                                  // send the INSERT now, so the average query below sees this review
        movie.setRating(reviewRepository.averageRating(movie.getId()));
        return Review.toResource(review);
    }

    @Transactional(readOnly = true)
    public ReviewResource getReviewById(long reviewId) {
        return reviewRepository.findById(reviewId).map(Review::toResource)
                .orElseThrow(() -> new NotFoundException("Review not found: " + reviewId));
    }
}
