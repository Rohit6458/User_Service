package com.HealthProTeach.User.Service.Records;

import java.time.LocalDate;

public record PatientSearchdto(String name,
                               String email,
                               String phoneNo,
                               Boolean isActive,
                               LocalDate dateFrom,
                               LocalDate dateTo) {
}
