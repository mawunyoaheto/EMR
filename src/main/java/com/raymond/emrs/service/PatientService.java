package com.raymond.emrs.service;

import com.raymond.emrs.entity.Patient;

import java.util.List;

public interface PatientService {
    public List<Patient> getAllPatients();
    public Patient getPatientById(long patientId);
    public Patient getOnePatientByOpdNo(long patientOpdNo);
    public Patient addPatient(Patient patient);
    public void deletePatient(long patientId);

}
