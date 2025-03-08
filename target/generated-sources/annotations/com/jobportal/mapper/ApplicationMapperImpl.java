package com.jobportal.mapper;

import com.jobportal.model.dto.ApplicationDto;
import com.jobportal.model.entity.Application;
import com.jobportal.model.enums.ApplicationStatus;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-08T22:07:04+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
public class ApplicationMapperImpl implements ApplicationMapper {

    @Override
    public ApplicationDto applicationToApplicationDto(Application application) {
        if ( application == null ) {
            return null;
        }

        ApplicationDto applicationDto = new ApplicationDto();

        applicationDto.setId( application.getId() );
        if ( application.getStatus() != null ) {
            applicationDto.setStatus( application.getStatus().name() );
        }

        return applicationDto;
    }

    @Override
    public Application applicationDtoToApplication(ApplicationDto applicationDto) {
        if ( applicationDto == null ) {
            return null;
        }

        Application application = new Application();

        application.setId( applicationDto.getId() );
        if ( applicationDto.getStatus() != null ) {
            application.setStatus( Enum.valueOf( ApplicationStatus.class, applicationDto.getStatus() ) );
        }

        return application;
    }
}
