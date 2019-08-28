package com.example.myalertsystem.model;

import lombok.*;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name="alertSeq", initialValue=1, allocationSize=1000)
public class Alert {


    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="alertSeq")
    @Column(name="alert_id")
    private Long alertId;

   // @NotEmpty
    @Column(name="name")
    private  String name;

    @URL
   // @NotEmpty
    @Column(name="url")
    private String url;

   // @NotEmpty
    @Column(name = "http_method")
    private String httpMethod;

   // @NotEmpty
    @Column(name = "control_period")
    private Long controlPeriod;

    @DateTimeFormat
    @Column(name ="submit_date")
    private Date submitDate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn( name = "alert_id")
    private Set<Response> responses;


}
