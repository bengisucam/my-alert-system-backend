package com.example.myalertsystem.services;

import com.example.myalertsystem.repository.AlertRepository;
import com.example.myalertsystem.model.Alert;
import com.example.myalertsystem.model.Response;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
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
        alert.setSubmitDate(currentDate);
        alert.setResponses(new HashSet<>());
//        getResponse(alert);
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


    @Async
    @Scheduled(fixedRate = 1000)
    public void getResponse(Alert alert) throws IOException {

        String desiredMethod = alert.getHttpMethod();
        String desiredUrl = alert.getUrl();
        URL url = new URL(desiredUrl);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod(desiredMethod);

        Response responseObj = new Response();
        responseObj.setTimeLeft(21);
        if(urlConnection.getResponseCode() != 200){
            responseObj.setResponseValue("0");
        }
        else { responseObj.setResponseValue("1");}

        Alert alertFromRequest = alertRepository.findByAlertId(alert.getAlertId());
        alertFromRequest.getResponses().add(responseObj);
        Alert savedAlert = alertRepository.save(alertFromRequest);

        System.out.println("Response Code:"+ urlConnection.getResponseCode());
        System.out.println(savedAlert.getResponses());
    }

}
