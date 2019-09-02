package com.example.myalertsystem.services;

import com.example.myalertsystem.model.Alert;
import com.example.myalertsystem.model.Response;
import com.example.myalertsystem.repository.AlertRepository;


import lombok.RequiredArgsConstructor;
import lombok.var;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Service
@Transactional
@EnableAsync
@EnableScheduling
@RequiredArgsConstructor
public class ResponseSchedulerService {

    private final AlertRepository alertRepository;


    @Async
    @Scheduled(fixedRate = 1000)
    public void scheduleResponse() throws IOException {
        try {
            List<Alert> alerts = alertRepository.findAll();
            for (Alert alert : alerts) {
                int timeLeft = alert.getTimeLeft();  // 0
                if (timeLeft == alert.getControlPeriod()) {
                    Response res = getResponseValue(alert);
                } else {
                    alert.setTimeLeft(timeLeft + 1);
                }
            }
        } catch (IOException io){
            System.out.println("Couldn't schedule!  " + io);
        }
    }

    private Response getResponseValue(Alert alert) throws IOException {

        HttpMethod desiredMethod = alert.getHttpMethod();
        String desiredUrl = alert.getUrl();
        URL url = new URL(desiredUrl);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod(desiredMethod.toString());

        var currentDate = new Date();
        Response responseObj = new Response();
        responseObj.setResponseTime(currentDate);

        if(urlConnection.getResponseCode() != 200){
            responseObj.setResponseValue("0");
        }
        else { responseObj.setResponseValue("1"); }

        Alert alertFromRequest = alertRepository.findByAlertId(alert.getAlertId());
        alertFromRequest.getResponses().add(responseObj);
        Alert savedAlert = alertRepository.save(alertFromRequest);

        System.out.println("Response Code:"+ urlConnection.getResponseCode());
        System.out.println(savedAlert.getResponses());

        return responseObj;
    }

}
