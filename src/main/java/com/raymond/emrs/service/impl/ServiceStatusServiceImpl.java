package com.raymond.emrs.service.impl;

import com.raymond.emrs.entity.ServiceStatus;
import com.raymond.emrs.exceptions.EmrException;
import com.raymond.emrs.exceptions.ResourceNotFoundException;
import com.raymond.emrs.repository.ServiceStatusRepository;
import com.raymond.emrs.service.ServiceStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceStatusServiceImpl implements ServiceStatusService {
    @Autowired
    ServiceStatusRepository serviceStatusRepository;

    @Override
    public ServiceStatus addServiceStatus(ServiceStatus serviceStatus) {
        ServiceStatus alreadyExists = serviceStatusRepository.findByDescription(
                serviceStatus.getDescription()).get();
        if (alreadyExists != null)
            return serviceStatusRepository.save(serviceStatus);
        throw new EmrException(HttpStatus.BAD_REQUEST, "Service Status already setup");
    }

    @Override
    public ServiceStatus getOneServiceStatus(long statusId) {
        return serviceStatusRepository.findById(statusId).orElseThrow(
                () -> new ResourceNotFoundException("Service Status not found" + statusId, HttpStatus.NOT_FOUND));
    }

    @Override
    public List<ServiceStatus> gatAllServiceStatus() {
        return serviceStatusRepository.findAll();
    }

    @Override
    public ServiceStatus updateServicesStatus(long statusId, ServiceStatus serviceStatus) {
        ServiceStatus foundServiceStatus = serviceStatusRepository.findById(statusId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Service Status not found " + statusId, HttpStatus.NOT_FOUND));
        foundServiceStatus.setDescription(serviceStatus.getDescription());
        return serviceStatusRepository.save(foundServiceStatus);
    }

    @Override
    public void deleteServiceStatus(long statusId) {
        ServiceStatus foundServiceStatus = serviceStatusRepository.findById(statusId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Failed, Service Status not found: " + statusId, HttpStatus.NOT_FOUND));
        serviceStatusRepository.deleteById(statusId);
    }
}
