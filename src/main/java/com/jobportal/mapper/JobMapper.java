package com.jobportal.mapper;

import com.jobportal.model.dto.JobDto;
import com.jobportal.model.entity.Job;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface JobMapper {
    JobMapper INSTANCE = Mappers.getMapper(JobMapper.class);

    @Mapping(source = "employer.id", target = "employerId")
    JobDto jobToJobDto(Job job);

    @Mapping(source = "employerId", target = "employer.id")
    Job jobDtoToJob(JobDto jobDto);
}

