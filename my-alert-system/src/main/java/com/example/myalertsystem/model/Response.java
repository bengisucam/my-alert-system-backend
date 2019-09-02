package com.example.myalertsystem.model;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Response {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="responseSeq")
    @Column(name = "response_id")
    private Long responseId;

    @Column(name = "response_value")
    private String responseValue;

    @DateTimeFormat
    @Column(name ="response_time")
    private Date responseTime;

}
