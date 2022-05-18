package com.raymond.emrs.controllers;

import com.raymond.emrs.entity.Patient;
import com.raymond.emrs.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping("{patientId}")
    public ResponseEntity<Patient> getOnePatient(@RequestParam Optional<String> opdno,
                                                 @PathVariable("patientId") long patientId){
        return new ResponseEntity<>(patientService.getPatientById(patientId), HttpStatus.OK);
    }

//    @GetMapping
//    public ResponseEntity<Patient> getOnePatient(@RequestParam String opdno){
//        System.out.println(opdno);
//        return new ResponseEntity<>(patientService.getPatientByOpdNo(opdno), HttpStatus.OK);
//    }

    @GetMapping
    public ResponseEntity<List<Patient>>getAllPatients() {
        return new ResponseEntity<>(patientService.getAllPatients(), HttpStatus.OK) ;
    }

    @PostMapping
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patientDetails){
        Patient patientResponse = patientService.addPatient(patientDetails);
        return new ResponseEntity<>(patientResponse, HttpStatus.CREATED);
    }


}
