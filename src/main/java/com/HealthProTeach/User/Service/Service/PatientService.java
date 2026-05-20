package com.HealthProTeach.User.Service.Service;

import com.HealthProTeach.User.Service.DTO.PatientSearchResponse;
import com.HealthProTeach.User.Service.MapStruct.PatientMapper;
import com.HealthProTeach.User.Service.Records.PatientDTO;
import com.HealthProTeach.User.Service.Records.PatientSearchdto;
import com.HealthProTeach.User.Service.Entity.Patient;
import com.HealthProTeach.User.Service.Enum.UserRole;
import com.HealthProTeach.User.Service.Repo.PatientRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {

    private final PatientRepo patientRepo;

    private final ModelMapper modelMapper;

    private  Patient patient;

    private final PatientMapper patientMapper;

    public PatientService(PatientRepo patientRepo, ModelMapper modelMapper, PatientMapper patientMapper) {
        this.patientRepo = patientRepo;
        this.modelMapper = modelMapper;
        this.patientMapper = patientMapper;
    }



    public String registerPatient(PatientDTO patientDTO) {
        Patient patient = patientMapper.toEntity(patientDTO);
        patient.setRole(UserRole.PATIENT);
        patient.setActive(true);
        return patientRepo.save(patient) != null
                ? "Patient Registered Successfully"
                : "Patient Registration Failed";
    }

    /*

    1. Create a method to search patients by name, email, phone number, date from, date to, and active status.
     */
    public List<PatientSearchResponse> getPatient(PatientSearchdto patientSearchdto) {
        List<Patient> patientList =
                patientRepo.searchPatientsBy(
                        patientSearchdto.name(),
                        patientSearchdto.email(),
                        patientSearchdto.phoneNo(),
                        patientSearchdto.dateFrom().atStartOfDay(),
                        patientSearchdto.dateTo().atStartOfDay(),
                        patientSearchdto.isActive()
                );
        List<PatientSearchResponse> patientSearchdtoList = new ArrayList<>();
        for(Patient patient : patientList){
            PatientSearchResponse patientSearchResponse=new PatientSearchResponse();
            patientSearchResponse.setName(patient.getName());
            patientSearchResponse.setEmail(patient.getEmail());
            patientSearchResponse.setPhoneNo(patient.getPhoneNo());
            patientSearchResponse.setActive(patient.getActive());
            patientSearchResponse.setDateFrom(patient.getCreatedDate().toLocalDate());
            patientSearchResponse.setDateTo(patient.getLastModifiedDate().toLocalDate());

            patientSearchdtoList.add(patientSearchResponse);
        }

        return patientSearchdtoList;
    }
}
