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

    public List<ApplicationDto> getApplicationsByJobId(Long jobId) {
        return applicationRepository.findByJobId(jobId)
                .stream()
                .map(ApplicationMapper.INSTANCE::applicationToApplicationDto)
                .collect(Collectors.toList());
    }

    public List<ApplicationDto> getApplicationsByUserId(Long userId) {
        return applicationRepository.findByJobSeekerId(userId)
                .stream()
                .map(ApplicationMapper.INSTANCE::applicationToApplicationDto)
                .collect(Collectors.toList());
    }

    public ApplicationDto applyForJob(ApplicationDto applicationDto) {
        Job job = jobRepository.findById(applicationDto.getJobId())
                .orElseThrow(() -> new RuntimeException("Job not found"));

        User jobSeeker = userRepository.findById(applicationDto.getJobSeekerId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Application application = new Application(job, jobSeeker, ApplicationStatus.PENDING);
        applicationRepository.save(application);

        return ApplicationMapper.INSTANCE.applicationToApplicationDto(application);
    }

    public ApplicationDto updateApplicationStatus(Long applicationId, String status) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        application.setStatus(ApplicationStatus.valueOf(status.toUpperCase()));
        applicationRepository.save(application);

        return ApplicationMapper.INSTANCE.applicationToApplicationDto(application);
    }
}
