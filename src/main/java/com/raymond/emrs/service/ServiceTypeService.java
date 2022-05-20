package com.raymond.emrs.service;

import com.raymond.emrs.entity.ServiceType;

import java.util.List;

public interface ServiceTypeService {
    public ServiceType addServiceType(ServiceType serviceType);
    public ServiceType getOneServiceType(long serviceTypeId);
    public List<ServiceType> getAllServiceTypes();
    public ServiceType updateServiceType(long serviceTypeId, ServiceType serviceType);
    public void deleteServiceType(long serviceTypeId);

}
