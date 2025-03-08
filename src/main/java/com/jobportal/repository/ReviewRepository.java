package com.jobportal.repository;


        import com.jobportal.model.entity.Review;
        import org.springframework.data.jpa.repository.JpaRepository;
        import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByJobId(Long jobId);
}
