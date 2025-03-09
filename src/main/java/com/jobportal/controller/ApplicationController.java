package com.jobportal.controller;

import com.jobportal.model.dto.ApplicationDto;
import com.jobportal.service.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

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
    public ApplicationDto applyForJob(@Valid @RequestBody ApplicationDto applicationDto) {
        return applicationService.applyForJob(applicationDto);
    }

    @PutMapping("/{applicationId}/status")
    public ApplicationDto updateApplicationStatus(@Valid
            @PathVariable Long applicationId,
            @RequestBody Map<String, String> requestBody) {

        // Extract the status from the request body
        String status = requestBody.get("status");

        return applicationService.updateApplicationStatus(applicationId, status);
    }

}
