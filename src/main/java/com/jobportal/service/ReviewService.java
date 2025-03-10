package com.jobportal.service;

import com.jobportal.mapper.ReviewMapper;
import com.jobportal.model.dto.ReviewDto;
import com.jobportal.model.entity.Job;
import com.jobportal.model.entity.Review;
import com.jobportal.repository.JobRepository;
import com.jobportal.repository.ReviewRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final JobRepository jobRepository;

    public ReviewService(ReviewRepository reviewRepository, JobRepository jobRepository) {
        this.reviewRepository = reviewRepository;
        this.jobRepository = jobRepository;
    }


    public Page<ReviewDto> getReviewsForJob(Long jobId, int page, int size, String sortBy, String sortDir) {
        Pageable pageable = PageRequest.of(
                page,
                size,
                sortDir.equalsIgnoreCase("DESC") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending()
        );

        return reviewRepository.findByJobId(jobId, pageable)
                .map(review -> ReviewMapper.INSTANCE.reviewToReviewDto(review));
    }

    public ReviewDto addReview(ReviewDto reviewDto) {
        Job job = jobRepository.findById(reviewDto.getJobId())
                .orElseThrow(() -> new RuntimeException("Job not found"));

        Review review = new Review(job, reviewDto.getReviewText(), reviewDto.getRating());
        reviewRepository.save(review);

        return ReviewMapper.INSTANCE.reviewToReviewDto(review);
    }
}
