package com.raymond.emrs.service.impl;

import com.raymond.emrs.entity.Archived;
import com.raymond.emrs.entity.Patient;
import com.raymond.emrs.exceptions.PatientNotFoundException;
import com.raymond.emrs.exceptions.ResourceNotFoundException;
import com.raymond.emrs.repository.PatientRepository;
import com.raymond.emrs.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@PropertySource("classpath:my.properties")
public class PatientServiceImpl implements PatientService {
    @Value("#{${ages:{22,17,19}}[1]}")
    Integer mike;

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient getPatientById(long patientId) {
        return patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientNotFoundException(patientId, HttpStatus.NOT_FOUND));
    }

    @Override
    public Patient getPatientByOpdNo(String opdNo) {
        Optional<Patient> foundPatient = Optional.ofNullable(patientRepository.getPatientByOpdNo(opdNo));
        if (!foundPatient.isPresent()) {
            throw new ResourceNotFoundException("Patient not found", HttpStatus.NOT_FOUND);
        }
        return foundPatient.get();
    }


    @Override
    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public void deletePatient(long patientId) {
        Patient foundPatient = patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientNotFoundException(
                        patientId, HttpStatus.NOT_FOUND));
        foundPatient.setArchived(Archived.YES);
        patientRepository.save(foundPatient);
    }
}
