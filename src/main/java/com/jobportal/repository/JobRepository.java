package com.jobportal.repository;

import com.jobportal.model.entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface JobRepository extends JpaRepository<Job, Long> {


    Page<Job> findByEmployerId(Long employerId, Pageable pageable);


    Page<Job> findByTitleContainingAndLocationContaining(String title, String location, Pageable pageable);

    @Transactional
    void deleteByEmployerId(Long employerId);
}
