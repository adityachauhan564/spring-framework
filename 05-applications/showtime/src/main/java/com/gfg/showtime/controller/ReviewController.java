package com.gfg.showtime.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gfg.showtime.resource.ReviewResource;
import com.gfg.showtime.service.ReviewService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // @AuthenticationPrincipal = the logged-in user, taken from the security context, not from the request body
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewResource addReview(@RequestBody @Valid ReviewResource reviewRequest, @AuthenticationPrincipal UserDetails user) {
        return reviewService.addReview(reviewRequest, user.getUsername());
    }

    @GetMapping("/find")
    public ReviewResource getReview(@RequestParam long reviewId) {
        return reviewService.getReviewById(reviewId);
    }
}
