package com.jobportal.model.dto;

public class ReviewDto {
    private Long id;
    private Long jobId;
    private String reviewText;
    private int rating; // e.g., from 1 to 5 stars

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
