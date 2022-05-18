package com.raymond.emrs.service.impl;

import com.raymond.emrs.entity.Address;
import com.raymond.emrs.entity.Patient;
import com.raymond.emrs.exceptions.ResourceNotFoundException;
import com.raymond.emrs.repository.AddressRepository;
import com.raymond.emrs.repository.PatientRepository;
import com.raymond.emrs.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    AddressRepository addressRepository;

    @Autowired
    PatientRepository patientRepository;

    @Override
    public Address addPatientAddress(long patientId, Address address) {
        Optional<Patient> foundPatient = patientRepository.findById(patientId);
        if(!foundPatient.isPresent()){
            throw new ResourceNotFoundException(
                    "failed, patient with given Id not found", HttpStatus.NOT_FOUND);
        }
        foundPatient.get().setAddress(address);
        return  addressRepository.save(address);
    }

    @Override
    public Address getOneAddress(long patientId, long addressId) {
        Patient foundPatient = patientRepository.findById(patientId)
                .orElseThrow(()->new ResourceNotFoundException(
                        "failed, patient with given Id not found", HttpStatus.NOT_FOUND));

        return foundPatient.getAddress();
    }

    @Override
    public void deleteOneAddress(long patientId, long addressId) {

        addressRepository.deleteById(addressId);

    }
}
