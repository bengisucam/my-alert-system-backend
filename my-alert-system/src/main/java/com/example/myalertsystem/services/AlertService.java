package com.example.myalertsystem.services;

import com.example.myalertsystem.repository.AlertRepository;
import com.example.myalertsystem.model.Alert;
import com.example.myalertsystem.model.Response;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Service
@Transactional
public class AlertService {

    private final AlertRepository alertRepository;

    @Autowired
    public AlertService(AlertRepository alertRepository){ this.alertRepository = alertRepository;}

    public Alert addAlert(final Alert alert){
        var currentDate = new Date();
        alert.setResponses(new HashSet<>());
        return alertRepository.save(alert);
    }

    public void deleteAlert(final Long alertId ){
        alertRepository.deleteByAlertId(alertId);
    }

    public List<Alert> getAlerts() { return  this.alertRepository.findAll(); }

    public Alert getSingleAlert(final Long alertId) { return  this.alertRepository.findByAlertId(alertId); }



    public Alert updateAlert(final Alert alert, final Long alertId) {
        Alert alert_from_db= alertRepository.findByAlertId(alertId);
        if(alert_from_db != null){
            alert.setAlertId(alert_from_db.getAlertId());
            return alertRepository.save(alert_from_db);
        }
        return null;
    }




}
