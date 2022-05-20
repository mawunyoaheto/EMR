package com.raymond.emrs.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class ServiceType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    private String extended_description;
    @JsonIgnore
    @OneToMany(mappedBy = "serviceType")
    private Set<ServiceCategory> serviceCategories = new HashSet<>();

    public ServiceType() {
    }

    public ServiceType(String description, String extended_description) {
        this.description = description;
        this.extended_description = extended_description;
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

    public String getExtended_description() {
        return extended_description;
    }

    public void setExtended_description(String extended_description) {
        this.extended_description = extended_description;
    }

    public Set<ServiceCategory> getServiceCategories() {
        return serviceCategories;
    }

    public void setServiceCategories(Set<ServiceCategory> serviceCategories) {
        this.serviceCategories = serviceCategories;
    }

    @Override
    public String toString() {
        return "ServiceType{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", extended_description='" + extended_description + '\'' +
                '}';
    }
}
