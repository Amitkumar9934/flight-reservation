package com.webapp.flight.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "passengers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;
    private String email;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;
}
