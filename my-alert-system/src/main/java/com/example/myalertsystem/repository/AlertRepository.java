package com.example.myalertsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.myalertsystem.model.Alert;

import java.util.List;

public interface AlertRepository extends  JpaRepository<Alert, Long>{

    void deleteByAlertId(Long alertId);
    Alert findByAlertId(Long alertId);

    @Override
    List<Alert> findAll();

    @Override
    Alert save(Alert alert);
}
