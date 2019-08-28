package com.example.myalertsystem.model;


import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SequenceGenerator(name="responseSeq", initialValue=1, allocationSize=1000)
public class Response {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="responseSeq")
   // @Column(name = "response_id")
    private Long responseId;

   // @Column(name = "time_left")
    private int timeLeft;

   // @Column(name = "response_value")
    private String responseValue;

}
