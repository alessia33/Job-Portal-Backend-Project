package com.jobportal.repository;


        import com.jobportal.model.entity.Job;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.transaction.annotation.Transactional;

        import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByEmployerId(Long employerId);
    @Transactional
    void deleteByEmployerId(Long employerId);
}
