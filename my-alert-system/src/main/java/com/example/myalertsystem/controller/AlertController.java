package com.example.myalertsystem.controller;

import org.springframework.web.bind.annotation.*;
import com.example.myalertsystem.model.Alert;
import com.example.myalertsystem.services.AlertService;

import java.util.List;


@RestController
@CrossOrigin("*")
public class AlertController {

    private final AlertService alertService;

    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    @PostMapping("/alerts")
    public Alert addAlert(@RequestBody final Alert alert){ return alertService.addAlert(alert);  }

    @DeleteMapping("/alerts/{alertId}")
    public void deleteAlert(@PathVariable final Long alertId){
        alertService.deleteAlert(alertId);
    }

    @GetMapping("/alerts")
    public List<Alert> getAlerts(){
        return alertService.getAlerts();
    }

    @PutMapping("/alerts")
    public Alert updateAlert(@RequestBody final Alert alert, @RequestParam Long alertId){
        return alertService.updateAlert(alert, alertId);
    }



}
