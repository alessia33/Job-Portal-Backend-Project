package com.jobportal.mapper;

import com.jobportal.model.dto.JobDto;
import com.jobportal.model.entity.Job;
import com.jobportal.model.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-10T01:12:45+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class JobMapperImpl implements JobMapper {

    @Override
    public JobDto jobToJobDto(Job job) {
        if ( job == null ) {
            return null;
        }

        JobDto jobDto = new JobDto();

        jobDto.setEmployerId( jobEmployerId( job ) );
        jobDto.setId( job.getId() );
        jobDto.setTitle( job.getTitle() );
        jobDto.setDescription( job.getDescription() );
        jobDto.setLocation( job.getLocation() );

        return jobDto;
    }

    @Override
    public Job jobDtoToJob(JobDto jobDto) {
        if ( jobDto == null ) {
            return null;
        }

        Job job = new Job();

        job.setId( jobDto.getId() );
        job.setTitle( jobDto.getTitle() );
        job.setDescription( jobDto.getDescription() );
        job.setLocation( jobDto.getLocation() );

        return job;
    }

    private Long jobEmployerId(Job job) {
        if ( job == null ) {
            return null;
        }
        User employer = job.getEmployer();
        if ( employer == null ) {
            return null;
        }
        Long id = employer.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
