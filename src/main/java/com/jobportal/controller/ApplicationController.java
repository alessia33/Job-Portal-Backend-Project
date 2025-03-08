package com.jobportal.controller;

import com.jobportal.model.dto.ApplicationDto;
import com.jobportal.service.ApplicationService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {
    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/job/{jobId}")
    public List<ApplicationDto> getApplicationsForJob(@PathVariable Long jobId) {
        return applicationService.getApplicationsByJobId(jobId);
    }

    @GetMapping("/user/{userId}")
    public List<ApplicationDto> getApplicationsForUser(@PathVariable Long userId) {
        return applicationService.getApplicationsByUserId(userId);
    }

    @PostMapping
    public ApplicationDto applyForJob(@RequestBody ApplicationDto applicationDto) {
        return applicationService.applyForJob(applicationDto);
    }

    @PutMapping("/{applicationId}/status/{status}")
    public ApplicationDto updateApplicationStatus(@PathVariable Long applicationId, @PathVariable String status) {
        return applicationService.updateApplicationStatus(applicationId, status);
    }
}
