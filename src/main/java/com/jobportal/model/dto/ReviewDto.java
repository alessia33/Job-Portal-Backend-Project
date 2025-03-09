package com.jobportal.model.dto;

import jakarta.validation.constraints.*;

public class ReviewDto {
    private Long id;

    @NotNull(message = "Job ID is required")
    private Long jobId;

    @NotBlank(message = "Review text cannot be empty")
    @Size(min = 10, max = 500, message = "Review text must be between 10 and 500 characters")
    private String reviewText;

    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating cannot be more than 5")
    private int rating;

    public ReviewDto() {}

    public ReviewDto(Long id, Long jobId, String reviewText, int rating) {
        this.id = id;
        this.jobId = jobId;
        this.reviewText = reviewText;
        this.rating = rating;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getJobId() { return jobId; }
    public void setJobId(Long jobId) { this.jobId = jobId; }

    public String getReviewText() { return reviewText; }
    public void setReviewText(String reviewText) { this.reviewText = reviewText; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }
}
