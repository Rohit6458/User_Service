package com.HealthProTeach.User.Service.Records;

import java.time.LocalTime;

/**
 * Request DTO for partially updating a doctor.
 */
public record DoctorUpdateDTO(
        String name,
        String email,
        String phone,
        String specialization,
        Double availableDays,
        LocalTime startTime,
        LocalTime endTime
) {
}
