package com.HealthProTeach.User.Service.Records;

public record PatientUpdateDTO(
        String name,
        String email,
        String phoneNumber
) {
}
