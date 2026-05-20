package com.HealthProTeach.User.Service.MapStruct;

import com.HealthProTeach.User.Service.Entity.Patient;
import com.HealthProTeach.User.Service.Records.PatientDTO;
import com.HealthProTeach.User.Service.Records.PatientSearchdto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    Patient toEntity(PatientDTO patientDTO);

    @Mapping(source = "createdDate", target = "dateFrom")
    @Mapping(source = "lastModifiedDate", target = "dateTo")
    @Mapping(source = "phoneNo", target = "phoneNo")
     List<PatientSearchdto> patientSearchdto(List<Patient> patientList);
}
