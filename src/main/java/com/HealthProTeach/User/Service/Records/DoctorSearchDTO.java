package com.HealthProTeach.User.Service.Records;

import java.time.LocalDate;

/**
 * Request DTO for searching doctors with optional filters.
 */
public record DoctorSearchDTO(
        String name,
        String email,
        String phone,
        String specialization,
        Boolean isActive,
        LocalDate dateFrom,
        LocalDate dateTo
) {
}
