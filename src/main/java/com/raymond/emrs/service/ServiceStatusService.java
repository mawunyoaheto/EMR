package com.raymond.emrs.service;

import com.raymond.emrs.entity.ServiceStatus;

import java.util.List;

public interface ServiceStatusService {
    public ServiceStatus addServiceStatus(ServiceStatus serviceStatus);
    public ServiceStatus getOneServiceStatus(long statusId);
    public List<ServiceStatus> gatAllServiceStatus();
    public ServiceStatus updateServicesStatus(long statusId, ServiceStatus serviceStatus);
    public void deleteServiceStatus(long statusId);

}
