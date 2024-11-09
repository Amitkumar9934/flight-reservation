package com.webapp.flight.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "reservations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String flightId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "reservation_id")
    private List<Passenger> passengers;
}
