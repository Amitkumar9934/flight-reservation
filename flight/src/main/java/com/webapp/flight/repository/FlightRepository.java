package com.webapp.flight.repository;

import com.webapp.flight.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight,Long> {

    List<Flight> findByDepartureAndArrivalAndDate(String departure, String arrival, String date);
}
