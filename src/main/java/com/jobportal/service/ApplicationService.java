package com.jobportal.service;

import com.jobportal.mapper.ApplicationMapper;
import com.jobportal.model.dto.ApplicationDto;
import com.jobportal.model.entity.Application;
import com.jobportal.model.entity.Job;
import com.jobportal.model.entity.User;
import com.jobportal.model.enums.ApplicationStatus;
import com.jobportal.repository.ApplicationRepository;
import com.jobportal.repository.JobRepository;
import com.jobportal.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    public ApplicationService(ApplicationRepository applicationRepository, JobRepository jobRepository, UserRepository userRepository) {
        this.applicationRepository = applicationRepository;
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }


    public Page<ApplicationDto> getApplicationsByJobId(Long jobId, int page, int size, String sortBy, String sortDir) {
        Pageable pageable = PageRequest.of(
                page, size, sortDir.equalsIgnoreCase("DESC") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending());

        return applicationRepository.findByJobId(jobId, pageable)
                .map(ApplicationMapper.INSTANCE::applicationToApplicationDto);
    }


    public Page<ApplicationDto> getApplicationsByUserId(Long userId, int page, int size, String sortBy, String sortDir) {
        Pageable pageable = PageRequest.of(
                page, size, sortDir.equalsIgnoreCase("DESC") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending());

        return applicationRepository.findByJobSeekerId(userId, pageable)
                .map(ApplicationMapper.INSTANCE::applicationToApplicationDto);
    }

    public ApplicationDto applyForJob(ApplicationDto applicationDto) {
        if (applicationDto.getJobId() == null) {
            throw new IllegalArgumentException("Job ID cannot be null when applying for a job.");
        }
        if (applicationDto.getJobSeekerId() == null) {
            throw new IllegalArgumentException("Job Seeker ID cannot be null when applying for a job.");
        }

        Job job = jobRepository.findById(applicationDto.getJobId())
                .orElseThrow(() -> new RuntimeException("Job with ID " + applicationDto.getJobId() + " not found"));

        User jobSeeker = userRepository.findById(applicationDto.getJobSeekerId())
                .orElseThrow(() -> new RuntimeException("User with ID " + applicationDto.getJobSeekerId() + " not found"));

        Application application = new Application(job, jobSeeker, ApplicationStatus.PENDING);
        applicationRepository.save(application);

        return ApplicationMapper.INSTANCE.applicationToApplicationDto(application);
    }

    public ApplicationDto updateApplicationStatus(Long applicationId, String status) {
        if (applicationId == null) {
            throw new IllegalArgumentException("Application ID must not be null.");
        }
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("Status must not be empty or null.");
        }

        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application with ID " + applicationId + " not found"));

        try {
            application.setStatus(ApplicationStatus.valueOf(status.toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid status: " + status);
        }

        applicationRepository.save(application);
        return ApplicationMapper.INSTANCE.applicationToApplicationDto(application);
    }
}
