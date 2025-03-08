package com.jobportal.model.dto;


public class ApplicationDto {
    private Long id;
    private Long jobId;
    private Long jobSeekerId;
    private String status;

    public ApplicationDto() {}

    public ApplicationDto(Long id, Long jobId, Long jobSeekerId, String status) {
        this.id = id;
        this.jobId = jobId;
        this.jobSeekerId = jobSeekerId;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getJobId() { return jobId; }
    public void setJobId(Long jobId) { this.jobId = jobId; }

    public Long getJobSeekerId() { return jobSeekerId; }
    public void setJobSeekerId(Long jobSeekerId) { this.jobSeekerId = jobSeekerId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
