package com.raymond.emrs.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raymond.emrs.entity.Address;
import com.raymond.emrs.service.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AddressController.class)
public class AddressControllerTest {
    ObjectMapper mapper;
    @Autowired
    private AddressController addressController;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private AddressService addressService;

    @BeforeEach
    public void start() {
        mapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Test adding address to a patient")
    public void shouldAddAddressToPatient() throws Exception {
        Address mockAddress = new Address();
        mockAddress.setId(1L);
        mockAddress.setStreet("1000N 4th St");
        mockAddress.setCity("Fairfield");
        mockAddress.setState("IA");
        mockAddress.setZipCode(52557);
        when(addressService.addPatientAddress(anyLong(), any(Address.class))).thenReturn(mockAddress);


        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/patients/12/address")
                        .content(mapper.writeValueAsString(mockAddress))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("street").value("1000N 4th St"))
                .andExpect(MockMvcResultMatchers.jsonPath("city").value("Fairfield"))
                .andExpect(MockMvcResultMatchers.jsonPath("state").value("IA"))
                .andExpect(MockMvcResultMatchers.jsonPath("zipCode").value(52557));

    }

    @Test
    public void shouldReturnOnePatientAddress() throws Exception {
        Address mockAddress = new Address();
        mockAddress.setId(4L);
        mockAddress.setStreet("1000N 4th St");
        mockAddress.setCity("Fairfield");
        mockAddress.setState("IA");
        mockAddress.setZipCode(52557);
        when(addressService.getOneAddress(anyLong(), anyLong())).thenReturn(mockAddress);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/patients/1/address/4"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(4L))
                .andExpect(MockMvcResultMatchers.jsonPath("street").value("1000N 4th St"))
                .andExpect(MockMvcResultMatchers.jsonPath("city").value("Fairfield"))
                .andExpect(MockMvcResultMatchers.jsonPath("state").value("IA"))
                .andExpect(MockMvcResultMatchers.jsonPath("zipCode").value(52557));
    }

    @Test
    public void shouldDeleteOneAddress() throws Exception {
//        when(addressService.deleteOneAddress(anyLong(), anyLong())).andReturn();
//        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/patients/1/address/4"))
//                .andExpect(status().isNoContent());
    }
}
