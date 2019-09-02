package com.example.myalertsystem.controller;

import com.example.myalertsystem.model.Response;
import org.springframework.web.bind.annotation.*;
import com.example.myalertsystem.model.Alert;
import com.example.myalertsystem.services.AlertService;

import java.io.IOException;
import java.util.List;


@RestController
@CrossOrigin("*")
public class AlertController {

    private final AlertService alertService;

    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    @PostMapping("/addAlert")
    public Alert addAlert(@RequestBody final Alert alert){ return alertService.addAlert(alert);  }

    @DeleteMapping("/deleteAlert/{alertId}")
    public void deleteAlert(@PathVariable final Long alertId){
        alertService.deleteAlert(alertId);
    }

    @GetMapping("/getAlerts")
    public List<Alert> getAlerts(){
        return alertService.getAlerts();
    }

    @GetMapping("/getSingleAlert/{alertId}")
    public Alert getSingleAlert(@PathVariable final Long alertId){
        return alertService.getSingleAlert(alertId);
    }

    @PutMapping("/putAlerts")
    public Alert updateAlert(@RequestBody final Alert alert, @RequestParam Long alertId){
        return alertService.updateAlert(alert, alertId);
    }


/*
    @PostMapping("/alerts/responses")
    public Response getResponse(final @RequestBody Alert alert) throws IOException {
        return alertService.getResponse(alert);
    }
*/



}
