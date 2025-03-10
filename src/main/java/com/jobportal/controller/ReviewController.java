package com.jobportal.controller;

import com.jobportal.model.dto.ReviewDto;
import com.jobportal.service.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @GetMapping("/job/{jobId}")
    public Page<ReviewDto> getReviewsForJob(
            @PathVariable Long jobId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDir
    ) {
        return reviewService.getReviewsForJob(jobId, page, size, sortBy, sortDir);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_EMPLOYER')")
    public ReviewDto addReview(@RequestBody ReviewDto reviewDto) {
        return reviewService.addReview(reviewDto);
    }
}
