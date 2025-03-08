package com.jobportal.mapper;


import com.jobportal.model.dto.ApplicationDto;
import com.jobportal.model.entity.Application;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ApplicationMapper {
    ApplicationMapper INSTANCE = Mappers.getMapper(ApplicationMapper.class);

    ApplicationDto applicationToApplicationDto(Application application);
    Application applicationDtoToApplication(ApplicationDto applicationDto);
}

