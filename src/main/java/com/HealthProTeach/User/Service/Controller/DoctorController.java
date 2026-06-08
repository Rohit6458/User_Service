package com.HealthProTeach.User.Service.Controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HealthProTeach.User.Service.DTO.DoctorSearchWrapper;
import com.HealthProTeach.User.Service.Entity.Doctor;
import com.HealthProTeach.User.Service.Records.DoctorDTO;
import com.HealthProTeach.User.Service.Records.DoctorSearchDTO;
import com.HealthProTeach.User.Service.Records.DoctorUpdateDTO;
import com.HealthProTeach.User.Service.Service.DoctorService;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    /**
     * POST /doctor/register
     * Register a new doctor.
     * Returns 201 CREATED with the saved doctor object.
     */
    @PostMapping("/register")
    public ResponseEntity<Doctor> registerDoctor(@RequestBody DoctorDTO doctorDTO) {
        Doctor savedDoctor = doctorService.registerDoctor(doctorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDoctor);
    }

    /**
     * GET /doctor/search
     * Search doctors with optional filters. Results are wrapped in a single object.
     * Returns 200 OK with DoctorSearchWrapper (totalCount + list of doctors).
     */
    @GetMapping("/search")
    public ResponseEntity<DoctorSearchWrapper> searchDoctors(@RequestBody DoctorSearchDTO doctorSearchDTO) {
        DoctorSearchWrapper result = doctorService.searchDoctors(doctorSearchDTO);
        return ResponseEntity.ok(result);
    }

    /**
     * GET /doctor/{id}
     * Get a single doctor by their ID.
     * Returns 200 OK with doctor data, or 404 NOT FOUND if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
        Optional<Doctor> doctor = doctorService.getDoctorById(id);
        return doctor
                .map(d -> ResponseEntity.ok().body(d))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * PUT /doctor/update/{id}
     * Partially update a doctor's details.
     * Returns 200 OK with updated doctor, or 404 NOT FOUND if not found.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id,
                                               @RequestBody DoctorUpdateDTO doctorUpdateDTO) {
        Optional<Doctor> updatedDoctor = doctorService.updateDoctor(id, doctorUpdateDTO);
        return updatedDoctor
                .map(d -> ResponseEntity.ok().body(d))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
