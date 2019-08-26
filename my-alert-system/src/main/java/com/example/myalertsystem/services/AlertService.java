package com.example.myalertsystem.services;

import com.example.myalertsystem.repository.AlertRepository;
import com.example.myalertsystem.model.Alert;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class AlertService {

    private final AlertRepository alertRepository;

    @Autowired
    public AlertService(AlertRepository alertRepository){ this.alertRepository = alertRepository;}

    public Alert addAlert(final Alert alert){
        var currentDate = new Date();
        alert.setSubmitDate(currentDate);
        return alertRepository.save(alert);
    }
    public void deleteAlert(final Long alertID ){
        alertRepository.deleteByAlertId(alertID);
    }

    public List<Alert> getAlerts() { return  this.alertRepository.findAll(); }

    public Alert updateAlert(final Alert alert, final Long alertId) {

        Alert alert_from_db= alertRepository.findByAlertId(alertId);
        if(alert_from_db != null){
            alert.setAlertId(alert_from_db.getAlertId());
            return alertRepository.save(alert_from_db);
        }
        return null;

    }





}
