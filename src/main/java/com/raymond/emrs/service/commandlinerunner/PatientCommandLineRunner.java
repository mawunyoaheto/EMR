package com.raymond.emrs.service.commandlinerunner;

import com.raymond.emrs.entity.Gender;
import com.raymond.emrs.entity.Patient;
import com.raymond.emrs.entity.PatientStatus;
import com.raymond.emrs.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class PatientCommandLineRunner implements CommandLineRunner {
    @Autowired
    PatientRepository patientRepository;
    @Override
    @Transactional
    public void run(String... args) throws Exception {

        List<Patient> listOfPatients = new ArrayList<>();
        Patient raymond  = new Patient("0001/21","Raymond","Mawunyo","Aheto", Gender.MALE, LocalDate.of(1975,02,05),"614-701-7379", PatientStatus.OUT,0.00);
        Patient mike  = new Patient("0002/21","Mike",null,"Aheto",Gender.MALE,LocalDate.of(1995,04,15),"614-701-7379", PatientStatus.OUT,0.00);
        Patient lisa  = new Patient("0003/21","Lisa",null,"Aheto",Gender.FEMALE,LocalDate.of(2005,04,9),"614-701-7379", PatientStatus.OUT,0.00);

        listOfPatients.add(raymond);
        listOfPatients.add(mike);
        listOfPatients.add(lisa);

        for(Patient p : listOfPatients){
            patientRepository.save(p);
        }

    }
}
