package com.jobportal.controller;

import com.jobportal.model.dto.ReviewDto;
import com.jobportal.service.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/job/{jobId}")
    public List<ReviewDto> getReviewsForJob(@PathVariable Long jobId) {
        return reviewService.getReviewsForJob(jobId);
    }

    @PostMapping
    public ReviewDto addReview(@RequestBody ReviewDto reviewDto) {
        return reviewService.addReview(reviewDto);
    }
}
