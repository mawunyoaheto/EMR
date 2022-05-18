package com.raymond.emrs.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.raymond.emrs.entity.Archived;
import com.raymond.emrs.entity.Gender;
import com.raymond.emrs.entity.Patient;
import com.raymond.emrs.entity.Status;
import com.raymond.emrs.service.PatientService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(PatientController.class)
class PatientControllerTest {

    @Autowired
    private PatientController patientController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    @Test
    @DisplayName("Test should pass when mock data is returned")
    public void shouldReturnOnePatient() throws Exception {

        /**
         * step 1: mock the data to be returned by the patient service class
         */
        Patient mockPatient = new Patient();
        mockPatient.setOpdNo("0023/22");
        mockPatient.setFirstName("James");
        mockPatient.setSurname("Katarikawe");
        mockPatient.setDateOfBirth(LocalDate.of(1995, 2, 15));
        mockPatient.setGender(Gender.MALE);
        mockPatient.setPhoneNumber("641-234-5645");
        mockPatient.setArchived(Archived.NO);
        mockPatient.setStatus(Status.OUT);

        when(patientService.getPatientById(anyLong())).thenReturn(mockPatient);

        /**
         * step 2: create a mock HTTP request to verify the expected results
         */
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/patients/12"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("opdNo").value("0023/22"))
                .andExpect(MockMvcResultMatchers.jsonPath("firstName").value("James"))
                .andExpect(MockMvcResultMatchers.jsonPath("surname").value("Katarikawe"))
                .andExpect(MockMvcResultMatchers.jsonPath("dateOfBirth").value("1995-02-15"))
                .andExpect(MockMvcResultMatchers.jsonPath("gender").value("MALE"))
                .andExpect(MockMvcResultMatchers.jsonPath("phoneNumber").value("641-234-5645"))
                .andExpect(MockMvcResultMatchers.jsonPath("archived").value("NO"))
                .andExpect(MockMvcResultMatchers.jsonPath("status").value("OUT"))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("Test should pass when new patient is added")
    public void addPatientTest() throws Exception {
        /**
         * step 1: mock the data to be returned by the patient service class
         */
        Patient mockPatient = new Patient();
        mockPatient.setPatientId(1L);
        mockPatient.setOpdNo("0023/22");
        mockPatient.setFirstName("Benjamin");
        mockPatient.setSurname("Batte");
        mockPatient.setDateOfBirth(LocalDate.of(1985, 4, 12));
        mockPatient.setGender(Gender.MALE);
        mockPatient.setPhoneNumber("641-312-8484");
        mockPatient.setArchived(Archived.NO);
        mockPatient.setStatus(Status.OUT);

        when(patientService.addPatient(any(Patient.class))).thenReturn(mockPatient);
        /**
         * step 2: create a mock HTTP request to verify the expected results
         */
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/patients")
                        .content(mapper.writeValueAsString(mockPatient))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("patientId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("opdNo").value("0023/22"))
                .andExpect(MockMvcResultMatchers.jsonPath("firstName").value("Benjamin"))
                .andExpect(MockMvcResultMatchers.jsonPath("surname").value("Batte"))
                .andExpect(MockMvcResultMatchers.jsonPath("dateOfBirth").value("1985-04-12"))
                .andExpect(MockMvcResultMatchers.jsonPath("gender").value("MALE"))
                .andExpect(MockMvcResultMatchers.jsonPath("phoneNumber").value("641-312-8484"))
                .andExpect(MockMvcResultMatchers.jsonPath("archived").value("NO"))
                .andExpect(MockMvcResultMatchers.jsonPath("status").value("OUT"));
    }
}