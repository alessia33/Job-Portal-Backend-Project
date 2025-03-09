package com.jobportal.controller;

import com.jobportal.model.dto.ResumeDto;
import com.jobportal.service.ResumeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resumes")
public class ResumeController {
    private final ResumeService resumeService;

    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadResume(@RequestBody ResumeDto resumeDto) {
        resumeService.uploadResume(resumeDto);
        return ResponseEntity.ok("Resume uploaded successfully!");
    }
}
