package com.HealthProTeach.User.Service.Repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.HealthProTeach.User.Service.Entity.Doctor;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Long> {

    /**
     * Search doctors by any combination of optional filters.
     * All parameters are nullable — if null, the filter is skipped.
     */
    @Query("""
            SELECT d
            FROM Doctor d
            WHERE
              (:name IS NULL OR LOWER(d.name) LIKE LOWER(CONCAT('%', :name, '%')))
            AND
              (:email IS NULL OR LOWER(d.email) LIKE LOWER(CONCAT('%', :email, '%')))
            AND
              (:phone IS NULL OR d.phone LIKE CONCAT('%', :phone, '%'))
            AND
              (:specialization IS NULL OR LOWER(d.specialization) LIKE LOWER(CONCAT('%', :specialization, '%')))
            AND
              (:isActive IS NULL OR d.isActive = :isActive)
            AND
              (:dateFrom IS NULL OR d.createdDate >= :dateFrom)
            AND
              (:dateTo IS NULL OR d.createdDate <= :dateTo)
            """)
    List<Doctor> searchDoctorsBy(
            String name,
            String email,
            String phone,
            String specialization,
            Boolean isActive,
            LocalDateTime dateFrom,
            LocalDateTime dateTo
    );
}
