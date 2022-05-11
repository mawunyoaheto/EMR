package com.raymond.emrs.repository;

import com.raymond.emrs.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("SELECT p FROM Patient p WHERE p.opdNo=:opdNo")
    public Patient getPatientByOpdNo(long opdNo);
}
