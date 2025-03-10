package com.jobportal.repository;

import com.jobportal.model.entity.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {


    Page<Application> findByJobId(Long jobId, Pageable pageable);

    Page<Application> findByJobSeekerId(Long jobSeekerId, Pageable pageable);
}
