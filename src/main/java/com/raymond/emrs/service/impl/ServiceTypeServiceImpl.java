package com.raymond.emrs.service.impl;

import com.raymond.emrs.entity.ServiceType;
import com.raymond.emrs.exceptions.EmrException;
import com.raymond.emrs.exceptions.ResourceNotFoundException;
import com.raymond.emrs.repository.ServiceTypeRepository;
import com.raymond.emrs.service.ServiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.List;

public class ServiceTypeServiceImpl implements ServiceTypeService {
    @Autowired
    ServiceTypeRepository serviceTypeRepository;
    @Override
    public ServiceType addServiceType(ServiceType serviceType) {
        ServiceType alreadyExists = serviceTypeRepository.findByDescription(serviceType.getDescription()).get();
        if(alreadyExists!=null){
            return serviceTypeRepository.save(serviceType);
        }
        throw  new EmrException(HttpStatus.BAD_REQUEST,"Service already exists");
    }

    @Override
    public ServiceType getOneServiceType(long serviceTypeId) {
        return serviceTypeRepository.findById(serviceTypeId).orElseThrow(
                ()-> new ResourceNotFoundException("Service Type not found",HttpStatus.NOT_FOUND));
    }

    @Override
    public List<ServiceType> getAllServiceTypes() {
        return serviceTypeRepository.findAll();
    }

    @Override
    public ServiceType updateServiceType(long serviceTypeId, ServiceType serviceType) {

        ServiceType foundServiceType = serviceTypeRepository.findById(serviceTypeId)
                .orElseThrow(()-> new ResourceNotFoundException("Service Type not found "+serviceTypeId, HttpStatus.NOT_FOUND));
        foundServiceType.setDescription(serviceType.getDescription());
        foundServiceType.setExtended_description(serviceType.getExtended_description());
        serviceTypeRepository.save(foundServiceType);
        return null;
    }

    @Override
    public void deleteServiceType(long serviceTypeId) {

    }
}
