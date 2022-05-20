package com.raymond.emrs.service;

import com.raymond.emrs.entity.ServiceCategory;

import java.util.List;

public interface ServiceCategoryService {
    public ServiceCategory addServiceCategory(long serviceTypeId, ServiceCategory serviceCategory);

    public ServiceCategory getOneServiceCategory(long serviceTypeId, long categoryId);

    public List<ServiceCategory> getAllServiceCategories();

    public void deleteServiceCategory(long serviceTypeId, long categoryId);

}
