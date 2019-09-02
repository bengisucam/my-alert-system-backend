package com.example.myalertsystem.model;

import lombok.*;
import org.hibernate.validator.constraints.URL;
import org.springframework.http.HttpMethod;

import javax.persistence.*;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Alert {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="alertSeq")
    @Column(name="alert_id")
    private Long alertId;

    @Column(name="name")
    private  String name;

    @URL
    @Column(name="url")
    private String url;

    @Column(name = "http_method")
    private HttpMethod httpMethod;

    @Column(name = "control_period")
    private Long controlPeriod;

    @Column(name = "time_left")
    private int timeLeft;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn( name = "alert_id")
    private Set<Response> responses;


}
