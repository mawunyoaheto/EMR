package com.raymond.emrs.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long patientId;
    private String opdNo;
    private String firstName;
    private String otherName;
    private String surname;
    private Gender gender;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private PatientStatus status;
    private double outstandingBalance;
    private Archived archived;
    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Address address;


    public Patient(String opdNo, String firstName, String otherName, String surname, Gender gender,
                   LocalDate dateOfBirth, String phoneNumber, PatientStatus status, double outstandingBalance) {
        this.opdNo = opdNo;
        this.firstName = firstName;
        this.otherName = otherName;
        this.surname = surname;
        this.gender=gender;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.outstandingBalance = outstandingBalance;
        this.archived =Archived.NO;
    }
}
