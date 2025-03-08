package com.jobportal.controller;

        import com.jobportal.model.dto.JobDto;
        import com.jobportal.service.JobService;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public List<JobDto> getAllJobs() {
        return jobService.getAllJobs();
    }

    @GetMapping("/{id}")
    public JobDto getJobById(@PathVariable Long id) {
        return jobService.getJobById(id);
    }

    @PostMapping
    public JobDto createJob(@RequestBody JobDto jobDto) {
        return jobService.createJob(jobDto);
    }

    @PutMapping("/{id}")
    public JobDto updateJob(@PathVariable Long id, @RequestBody JobDto jobDto) {
        return jobService.updateJob(id, jobDto);
    }

    @DeleteMapping("/{id}")
    public void deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
    }
}
