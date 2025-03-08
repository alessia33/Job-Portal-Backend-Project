package com.jobportal.mapper;

        import com.jobportal.model.dto.ReviewDto;
        import com.jobportal.model.entity.Review;
        import org.mapstruct.Mapper;
        import org.mapstruct.Mapping;
        import org.mapstruct.factory.Mappers;

@Mapper
public interface ReviewMapper {
    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    @Mapping(source = "job.id", target = "jobId")
    ReviewDto reviewToReviewDto(Review review);

    @Mapping(source = "jobId", target = "job.id")
    Review reviewDtoToReview(ReviewDto reviewDto);
}
