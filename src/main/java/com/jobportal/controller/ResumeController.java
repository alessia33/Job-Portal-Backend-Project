package com.jobportal.controller;

import com.jobportal.model.dto.ResumeDto;
import com.jobportal.service.ResumeService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resumes")
public class ResumeController {
    private final ResumeService resumeService;

    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @PostMapping("/upload")
    @PreAuthorize("hasAuthority('ROLE_JOB_SEEKER')")
    public ResponseEntity<String> uploadResume(@RequestBody ResumeDto resumeDto) {
        resumeService.uploadResume(resumeDto);
        return ResponseEntity.ok("Resume uploaded successfully!");
    }

    @GetMapping
    public ResponseEntity<Page<ResumeDto>> getAllResumes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "DESC") String sortDir
    ) {
        Page<ResumeDto> resumes = resumeService.getAllResumes(page, size, sortBy, sortDir);
        return ResponseEntity.ok(resumes);
    }
}
