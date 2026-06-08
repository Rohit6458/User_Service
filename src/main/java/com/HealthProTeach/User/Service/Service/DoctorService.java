package com.HealthProTeach.User.Service.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.HealthProTeach.User.Service.DTO.DoctorSearchResponse;
import com.HealthProTeach.User.Service.DTO.DoctorSearchWrapper;
import com.HealthProTeach.User.Service.Entity.Doctor;
import com.HealthProTeach.User.Service.MapStruct.DoctorMapper;
import com.HealthProTeach.User.Service.Records.DoctorDTO;
import com.HealthProTeach.User.Service.Records.DoctorSearchDTO;
import com.HealthProTeach.User.Service.Records.DoctorUpdateDTO;
import com.HealthProTeach.User.Service.Repo.DoctorRepo;

@Service
public class DoctorService {

    private final DoctorRepo doctorRepo;
    private final DoctorMapper doctorMapper;

    public DoctorService(DoctorRepo doctorRepo, DoctorMapper doctorMapper) {
        this.doctorRepo = doctorRepo;
        this.doctorMapper = doctorMapper;
    }

    /**
     * Register a new doctor. Sets isActive = true by default.
     *
     * @return saved Doctor entity
     */
    public Doctor registerDoctor(DoctorDTO doctorDTO) {
        Doctor doctor = doctorMapper.toEntity(doctorDTO);
        doctor.setIsActive(true);
        return doctorRepo.save(doctor);
    }

    /**
     * Search doctors with optional filters, returns results wrapped in a single object.
     *
     * @return DoctorSearchWrapper containing total count and list of matching doctors
     */
    public DoctorSearchWrapper searchDoctors(DoctorSearchDTO doctorSearchDTO) {
        List<Doctor> doctorList = doctorRepo.searchDoctorsBy(
                doctorSearchDTO.name(),
                doctorSearchDTO.email(),
                doctorSearchDTO.phone(),
                doctorSearchDTO.specialization(),
                doctorSearchDTO.isActive(),
                doctorSearchDTO.dateFrom() != null ? doctorSearchDTO.dateFrom().atStartOfDay() : null,
                doctorSearchDTO.dateTo() != null ? doctorSearchDTO.dateTo().atStartOfDay() : null
        );

        List<DoctorSearchResponse> responseList = new ArrayList<>();
        for (Doctor doctor : doctorList) {
            DoctorSearchResponse response = new DoctorSearchResponse();
            response.setId(doctor.getId());
            response.setName(doctor.getName());
            response.setEmail(doctor.getEmail());
            response.setPhone(doctor.getPhone());
            response.setSpecialization(doctor.getSpecialization());
            response.setLicenceNumber(doctor.getLicenceNumber());
            response.setIsActive(doctor.getIsActive());
            response.setAvailableDays(doctor.getAvailableDays());
            response.setStartTime(doctor.getStartTime());
            response.setEndTime(doctor.getEndTime());
            response.setCreatedAt(doctor.getCreatedDate() != null ? doctor.getCreatedDate().toLocalDate() : null);
            response.setUpdatedAt(doctor.getLastModifiedDate() != null ? doctor.getLastModifiedDate().toLocalDate() : null);
            responseList.add(response);
        }

        return new DoctorSearchWrapper(responseList);
    }

    /**
     * Partially update a doctor by ID. Only non-null, non-blank fields are updated.
     *
     * @return updated Doctor entity, or empty if not found
     */
    public Optional<Doctor> updateDoctor(Long id, DoctorUpdateDTO doctorUpdateDTO) {
        Optional<Doctor> optionalDoctor = doctorRepo.findById(id);
        if (optionalDoctor.isEmpty()) {
            return Optional.empty();
        }

        Doctor doctor = optionalDoctor.get();

        if (doctorUpdateDTO.name() != null && !doctorUpdateDTO.name().isBlank()) {
            doctor.setName(doctorUpdateDTO.name());
        }
        if (doctorUpdateDTO.email() != null && !doctorUpdateDTO.email().isBlank()) {
            doctor.setEmail(doctorUpdateDTO.email());
        }
        if (doctorUpdateDTO.phone() != null && !doctorUpdateDTO.phone().isBlank()) {
            doctor.setPhone(doctorUpdateDTO.phone());
        }
        if (doctorUpdateDTO.specialization() != null && !doctorUpdateDTO.specialization().isBlank()) {
            doctor.setSpecialization(doctorUpdateDTO.specialization());
        }
        if (doctorUpdateDTO.availableDays() != null) {
            doctor.setAvailableDays(doctorUpdateDTO.availableDays());
        }
        if (doctorUpdateDTO.startTime() != null) {
            doctor.setStartTime(doctorUpdateDTO.startTime());
        }
        if (doctorUpdateDTO.endTime() != null) {
            doctor.setEndTime(doctorUpdateDTO.endTime());
        }

        return Optional.of(doctorRepo.save(doctor));
    }

    /**
     * Get a single doctor by ID.
     *
     * @return Optional Doctor entity
     */
    public Optional<Doctor> getDoctorById(Long id) {
        return doctorRepo.findById(id);
    }
}
