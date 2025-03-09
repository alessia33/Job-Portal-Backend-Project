package com.jobportal.mapper;

import com.jobportal.model.dto.JobDto;
import com.jobportal.model.entity.Job;
import com.jobportal.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface JobMapper {
    JobMapper INSTANCE = Mappers.getMapper(JobMapper.class);


    @Mapping(source = "employer.id", target = "employerId")
    JobDto jobToJobDto(Job job);


    @Mapping(source = "employerId", target = "employer", ignore = true) // This prevents direct mapping
    Job jobDtoToJob(JobDto jobDto);
}
