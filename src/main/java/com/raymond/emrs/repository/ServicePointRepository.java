package com.raymond.emrs.repository;

import com.raymond.emrs.entity.ServicePoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicePointRepository extends JpaRepository<ServicePoint, Long> {
}
