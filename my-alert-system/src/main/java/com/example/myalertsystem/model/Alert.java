package com.example.myalertsystem.model;

import lombok.*;
import org.hibernate.validator.constraints.URL;
import org.springframework.http.HttpMethod;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Alert {


    @Id  // tablo için id oluşturur?
    @GeneratedValue  //counter tutar, yeni value gelince id yi kendisi unique bi şekilde artırır
    @Column(name = "alertId")
    private Long alertId;

    @NotEmpty
   // @Column(name= "name")
    private  String name;

    @URL
    @NotEmpty
   // @Column(name = "url")
    private String url;

    @NotEmpty
   // @Column(name = "httpMethod")
    private String httpMethod;

    @NotEmpty
   // @Column(name = "controlPeriod")
    private Long controlPeriod;


    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    // @Column(name = "submitDate")
    private Date submitDate;


}
