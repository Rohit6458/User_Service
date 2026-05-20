package com.HealthProTeach.User.Service.Repo;

import com.HealthProTeach.User.Service.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Long> {

    @Query("""
SELECT p
FROM Patient p
WHERE
(:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')))
AND
(:email IS NULL OR LOWER(p.email) LIKE LOWER(CONCAT('%', :email, '%')))
AND
(:phoneNo IS NULL OR p.phoneNo LIKE CONCAT('%', :phoneNo, '%'))
AND
(:isActive IS NULL OR p.isActive = :isActive)
AND
(:dateFrom IS NULL OR p.createdDate >= :dateFrom)
AND
(:dateTo IS NULL OR p.createdDate <= :dateTo)
""")
    List<Patient> searchPatientsBy(
            String name,
            String email,
            String phoneNo,
            LocalDateTime dateFrom,
            LocalDateTime dateTo,
            Boolean isActive
    );
}
