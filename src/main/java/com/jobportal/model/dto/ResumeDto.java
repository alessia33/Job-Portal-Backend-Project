package com.jobportal.model.dto;

import jakarta.validation.constraints.*;

public class ResumeDto {
    private Long id;

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotBlank(message = "File name cannot be empty")
    private String fileName;

    @NotBlank(message = "File content cannot be empty")
    private String fileContent;


    public ResumeDto() {}


    public ResumeDto(Long userId, String fileName, String fileContent) {
        this.userId = userId;
        this.fileName = fileName;
        this.fileContent = fileContent;
    }


    public ResumeDto(Long id, Long userId, String fileName, String fileContent) {
        this.id = id;
        this.userId = userId;
        this.fileName = fileName;
        this.fileContent = fileContent;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }

    public String getFileContent() { return fileContent; }
    public void setFileContent(String fileContent) { this.fileContent = fileContent; }
}
