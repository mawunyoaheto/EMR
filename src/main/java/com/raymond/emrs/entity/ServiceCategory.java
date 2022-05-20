package com.raymond.emrs.entity;

import javax.persistence.*;

@Entity
public class ServiceCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    @ManyToOne(cascade = CascadeType.ALL)
    private ServiceType serviceType;

    public ServiceCategory() {
    }

    public ServiceCategory(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    @Override
    public String toString() {
        return "ServiceCategory{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", serviceType=" + serviceType +
                '}';
    }
}
