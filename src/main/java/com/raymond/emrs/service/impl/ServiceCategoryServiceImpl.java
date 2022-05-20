package com.raymond.emrs.service.impl;

import com.raymond.emrs.entity.ServiceCategory;
import com.raymond.emrs.entity.ServiceType;
import com.raymond.emrs.exceptions.ResourceNotFoundException;
import com.raymond.emrs.repository.ServiceCategoryRepository;
import com.raymond.emrs.repository.ServiceTypeRepository;
import com.raymond.emrs.service.ServiceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCategoryServiceImpl implements ServiceCategoryService {
    @Autowired
    ServiceCategoryRepository serviceCategoryRepository;

    @Autowired
    ServiceTypeRepository serviceTypeRepository;

    @Override
    public ServiceCategory addServiceCategory(long serviceTypeId, ServiceCategory serviceCategory) {
        ServiceType foundServiceType = serviceTypeRepository.findById(serviceTypeId).get();
        if(foundServiceType!=null){
            foundServiceType.getServiceCategories().add(serviceCategory);
            serviceCategory.setServiceType(foundServiceType);
            return serviceCategory;
        }
        throw new ResourceNotFoundException("Service Type not found"+serviceTypeId, HttpStatus.NOT_FOUND);
    }

    @Override
    public ServiceCategory getOneServiceCategory(long serviceTypeId, long categoryId) {
        return serviceCategoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException(
                        "Service Category not found"+categoryId,HttpStatus.NOT_FOUND));
    }

    @Override
    public List<ServiceCategory> getAllServiceCategories() {
        return serviceCategoryRepository.findAll();
    }

    @Override
    public void deleteServiceCategory(long serviceTypeId, long categoryId) {

    }
}
