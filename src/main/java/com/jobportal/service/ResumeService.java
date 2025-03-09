package com.jobportal.service;

import com.jobportal.model.dto.ResumeDto;
import com.jobportal.model.entity.Resume;
import com.jobportal.repository.ResumeRepository;
import org.springframework.stereotype.Service;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@Service
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final String uploadDir = "uploads/resumes/";

    public ResumeService(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

    public void uploadResume(ResumeDto resumeDto) {
        try {
            byte[] fileBytes = Base64.getDecoder().decode(resumeDto.getFileContent());
            Path filePath = Paths.get(uploadDir + resumeDto.getFileName());
            Files.write(filePath, fileBytes);

            Resume resume = new Resume();
            resume.setJobSeekerId(resumeDto.getUserId());
            resume.setFilePath(filePath.toString());
            resumeRepository.save(resume);
        } catch (Exception e) {
            throw new RuntimeException("Error saving resume", e);
        }
    }
}
