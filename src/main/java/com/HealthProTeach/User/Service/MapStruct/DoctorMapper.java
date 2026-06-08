package com.HealthProTeach.User.Service.MapStruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.HealthProTeach.User.Service.Entity.Doctor;
import com.HealthProTeach.User.Service.Records.DoctorDTO;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    @Mapping(source = "startTime", target = "startTime")
    @Mapping(source = "endTime", target = "endTime")
    Doctor toEntity(DoctorDTO doctorDTO);
}
