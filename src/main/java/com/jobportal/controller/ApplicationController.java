package com.jobportal.controller;

import com.jobportal.model.dto.ApplicationDto;
import com.jobportal.service.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {
    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/job/{jobId}")
    public Page<ApplicationDto> getApplicationsForJob(
            @PathVariable Long jobId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDir) {
        return applicationService.getApplicationsByJobId(jobId, page, size, sortBy, sortDir);
    }

    @GetMapping("/user/{userId}")
    public Page<ApplicationDto> getApplicationsForUser(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDir) {
        return applicationService.getApplicationsByUserId(userId, page, size, sortBy, sortDir);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_JOB_SEEKER')")
    public ApplicationDto applyForJob(@Valid @RequestBody ApplicationDto applicationDto) {
        return applicationService.applyForJob(applicationDto);
    }

    @PutMapping("/{applicationId}/status")
    @PreAuthorize("hasAuthority('ROLE_EMPLOYER')")
    public ApplicationDto updateApplicationStatus(@Valid
                                                  @PathVariable Long applicationId,
                                                  @RequestBody Map<String, String> requestBody) {
        // Extract the status from the request body
        String status = requestBody.get("status");
        return applicationService.updateApplicationStatus(applicationId, status);
    }
}
