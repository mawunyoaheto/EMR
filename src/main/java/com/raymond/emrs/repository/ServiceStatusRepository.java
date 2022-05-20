package com.raymond.emrs.repository;

import com.raymond.emrs.entity.ServiceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceStatusRepository extends JpaRepository<ServiceStatus, Long> {
    Optional<ServiceStatus> findByDescription(String description);

}
