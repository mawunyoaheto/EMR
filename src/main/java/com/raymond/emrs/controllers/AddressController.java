package com.raymond.emrs.controllers;

import com.raymond.emrs.entity.Address;
import com.raymond.emrs.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AddressController {
    @Autowired
    AddressService addressService;

    @GetMapping(path = "/api/v1/patients/{patientId}/address/{addressId}")
    public ResponseEntity<Address> getOneAddress(@PathVariable long patientId, @PathVariable long addressId) {
        return new ResponseEntity<>(addressService.getOneAddress(patientId, addressId), HttpStatus.OK);
    }

    @PostMapping(path = "/api/v1/patients/{patientId}/address")
    public ResponseEntity<Address> addAddress(@PathVariable long patientId, @RequestBody Address address) {
        return new ResponseEntity<>(addressService.addPatientAddress(patientId, address), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/api/v1/patients{patientId}/address/{addressId}")
    public ResponseEntity<Void> deleteOneAddress(@PathVariable long patientId, @PathVariable long addressId) {
        addressService.deleteOneAddress(patientId, addressId);
        return ResponseEntity.noContent().build();
    }
}
