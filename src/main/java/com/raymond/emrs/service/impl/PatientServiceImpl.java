package com.raymond.emrs.service.impl;

import com.raymond.emrs.entity.Archived;
import com.raymond.emrs.entity.Patient;
import com.raymond.emrs.exceptions.ResourceNotFoundException;
import com.raymond.emrs.repository.PatientRepository;
import com.raymond.emrs.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient getPatientById(long patientId) {
        Optional<Patient> foundPatient = patientRepository.findById(patientId);
        if(!foundPatient.isPresent()){
            throw new ResourceNotFoundException("Patient not found",HttpStatus.NOT_FOUND);
        }
        return foundPatient.get();
    }

    @Override
    public Patient getPatientByOpdNo(String opdNo) {
        Optional<Patient> foundPatient = Optional.ofNullable(patientRepository.getPatientByOpdNo(opdNo));
        if(!foundPatient.isPresent()){
            throw new ResourceNotFoundException("Patient not found",HttpStatus.NOT_FOUND);
        }
        return foundPatient.get();
    }


    @Override
    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public void deletePatient(long patientId) {
        Optional<Patient> foundPatient = patientRepository.findById(patientId);
        if(!foundPatient.isPresent()){
            throw new ResourceNotFoundException("Delete failed, patient with given Id not found", HttpStatus.NOT_FOUND);
        }
        foundPatient.get().setArchived(Archived.YES);
        patientRepository.save(foundPatient.get());
    }
}
