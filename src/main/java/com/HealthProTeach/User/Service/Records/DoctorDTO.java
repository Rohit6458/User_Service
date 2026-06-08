package com.HealthProTeach.User.Service.Records;

import java.time.LocalTime;

/**
 * Request DTO for registering a new doctor.
 */
public record DoctorDTO(
        String name,
        String email,
        String phone,
        String specialization,
        String licenceNumber,
        Double availableDays,
        LocalTime startTime,
        LocalTime endTime
) {
}
