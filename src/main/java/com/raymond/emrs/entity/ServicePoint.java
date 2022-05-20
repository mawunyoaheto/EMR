package com.raymond.emrs.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ServicePoint {
    @Id
    @GeneratedValue
    private Long Id;
    private String code;
    private String description;
    private Gender allowed_gender;
    private Status status;

    public ServicePoint() {
    }

    public ServicePoint(String code, String description, Gender allowed_gender, Status status) {
        this.code = code;
        this.description = description;
        this.allowed_gender = allowed_gender;
        this.status = status;
    }

    public Long getId() {
        return Id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Gender getAllowed_gender() {
        return allowed_gender;
    }

    public void setAllowed_gender(Gender allowed_gender) {
        this.allowed_gender = allowed_gender;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ServicePoint{" +
                "Id=" + Id +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", allowed_gender=" + allowed_gender +
                ", status=" + status +
                '}';
    }
}
