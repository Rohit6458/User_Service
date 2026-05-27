package com.HealthProTeach.User.Service.Controller;

import com.HealthProTeach.User.Service.DTO.PatientSearchResponse;
import com.HealthProTeach.User.Service.Records.PatientDTO;
import com.HealthProTeach.User.Service.Records.PatientSearchdto;
import com.HealthProTeach.User.Service.Records.PatientUpdateDTO;
import com.HealthProTeach.User.Service.Service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/patient")
public class PatientController {

    private final PatientService patientService;
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/register")
    public String registerPatient(@RequestBody PatientDTO patientDTO ) {
        return patientService.registerPatient(patientDTO);
    }

    @GetMapping("/getPatient")
    public List<PatientSearchResponse> getPatient(@RequestBody PatientSearchdto patientSearchdto){
        return patientService.getPatient(patientSearchdto);
    }

    @PutMapping("/update/{id}")
    public String updatePatient(@PathVariable Long id, @RequestBody PatientUpdateDTO patientUpdateDTO) {
        return patientService.updatePatient(id, patientUpdateDTO);
    }

}
