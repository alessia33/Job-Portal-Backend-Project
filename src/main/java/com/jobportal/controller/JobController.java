package com.jobportal.controller;

import com.jobportal.model.dto.JobDto;
import com.jobportal.service.JobService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public List<JobDto> getAllJobs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return jobService.getAllJobs(page, size);
    }

    @GetMapping("/search")
    public List<JobDto> getFilteredJobs(
            @RequestParam(defaultValue = "") String title,
            @RequestParam(defaultValue = "") String location,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return jobService.getFilteredJobs(title, location, page, size);
    }

    @GetMapping("/{id}")
    public JobDto getJobById(@PathVariable Long id) {
        return jobService.getJobById(id);
    }

    @PreAuthorize("hasAuthority('ROLE_EMPLOYER')")
    @PostMapping
    public JobDto createJob(@RequestBody JobDto jobDto) {
        return jobService.createJob(jobDto);
    }

    @PutMapping("/{id}")
    public JobDto updateJob(@PathVariable Long id, @RequestBody JobDto jobDto) {
        return jobService.updateJob(id, jobDto);
    }

    @DeleteMapping("/{id}")
    public void deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
    }
}
