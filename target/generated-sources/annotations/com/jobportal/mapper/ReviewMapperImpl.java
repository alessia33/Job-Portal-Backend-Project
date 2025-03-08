package com.jobportal.mapper;

import com.jobportal.model.dto.ReviewDto;
import com.jobportal.model.entity.Job;
import com.jobportal.model.entity.Review;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-08T22:07:04+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
public class ReviewMapperImpl implements ReviewMapper {

    @Override
    public ReviewDto reviewToReviewDto(Review review) {
        if ( review == null ) {
            return null;
        }

        ReviewDto reviewDto = new ReviewDto();

        reviewDto.setJobId( reviewJobId( review ) );
        reviewDto.setId( review.getId() );
        reviewDto.setReviewText( review.getReviewText() );
        reviewDto.setRating( review.getRating() );

        return reviewDto;
    }

    @Override
    public Review reviewDtoToReview(ReviewDto reviewDto) {
        if ( reviewDto == null ) {
            return null;
        }

        Review review = new Review();

        review.setJob( reviewDtoToJob( reviewDto ) );
        review.setId( reviewDto.getId() );
        review.setReviewText( reviewDto.getReviewText() );
        review.setRating( reviewDto.getRating() );

        return review;
    }

    private Long reviewJobId(Review review) {
        if ( review == null ) {
            return null;
        }
        Job job = review.getJob();
        if ( job == null ) {
            return null;
        }
        Long id = job.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected Job reviewDtoToJob(ReviewDto reviewDto) {
        if ( reviewDto == null ) {
            return null;
        }

        Job job = new Job();

        job.setId( reviewDto.getJobId() );

        return job;
    }
}
