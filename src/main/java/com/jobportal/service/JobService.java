package com.jobportal.service;


import com.jobportal.mapper.JobMapper;
import com.jobportal.model.dto.JobDto;
import com.jobportal.model.entity.Job;
import com.jobportal.model.entity.User;
import com.jobportal.repository.JobRepository;
import com.jobportal.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobService {
    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    public JobService(JobRepository jobRepository, UserRepository userRepository) {
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }

    public List<JobDto> getAllJobs() {
        return jobRepository.findAll()
                .stream()
                .map(JobMapper.INSTANCE::jobToJobDto)
                .collect(Collectors.toList());
    }

    public JobDto getJobById(Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));
        return JobMapper.INSTANCE.jobToJobDto(job);
    }

    public JobDto createJob(JobDto jobDto) {
        User employer = userRepository.findById(jobDto.getEmployerId())
                .orElseThrow(() -> new RuntimeException("Employer not found"));

        Job job = new Job(jobDto.getTitle(), jobDto.getDescription(), jobDto.getLocation(), employer);
        jobRepository.save(job);

        return JobMapper.INSTANCE.jobToJobDto(job);
    }

    public JobDto updateJob(Long id, JobDto jobDto) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        job.setTitle(jobDto.getTitle());
        job.setDescription(jobDto.getDescription());
        job.setLocation(jobDto.getLocation());
        jobRepository.save(job);

        return JobMapper.INSTANCE.jobToJobDto(job);
    }

    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }
}
