package com.raymond.emrs.service;

import com.raymond.emrs.entity.Address;

public interface AddressService {

    public Address addPatientAddress(long patientId, Address address);
    public Address getOneAddress(long patientId, long addressId);
    public void deleteOneAddress(long patientId, long addressId);
}
