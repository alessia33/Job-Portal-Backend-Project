package com.jobportal.model.dto;

import jakarta.validation.constraints.*;

public class JobDto {
    private Long id;

    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    private String title;

    @NotBlank(message = "Description cannot be empty")
    private String description;

    @NotBlank(message = "Location is required")
    private String location;

    @NotNull(message = "Employer ID is required")
    private Long employerId;

    public JobDto() {}

    public JobDto(Long id, String title, String description, String location, Long employerId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.employerId = employerId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public Long getEmployerId() { return employerId; }
    public void setEmployerId(Long employerId) { this.employerId = employerId; }
}
