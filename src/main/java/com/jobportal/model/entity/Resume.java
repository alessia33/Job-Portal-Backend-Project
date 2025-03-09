package com.jobportal.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "resumes")
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "job_seeker_id")
    private Long jobSeekerId;

    @Column(name = "file_path")
    private String filePath;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getJobSeekerId() { return jobSeekerId; }
    public void setJobSeekerId(Long jobSeekerId) { this.jobSeekerId = jobSeekerId; }

    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }
}
