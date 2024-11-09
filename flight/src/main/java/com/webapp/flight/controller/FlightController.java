package com.webapp.flight.controller;

import com.webapp.flight.entity.Flight;
import com.webapp.flight.exception.FlightNotFoundException;
import com.webapp.flight.payload.ReservationRequestDTO;
import com.webapp.flight.payload.SearchCriteriaDTO;
import com.webapp.flight.service.FlightService;
import com.webapp.flight.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class FlightController {

    private FlightService flightService;

    private ReservationService reservationService;

    public FlightController(FlightService flightService, ReservationService reservationService) {
        this.flightService = flightService;
        this.reservationService = reservationService;
    }

    @PostMapping("/addflight")
    public ResponseEntity<Flight> addFlight(@RequestBody Flight flight){
        Flight flights = flightService.addFlight(flight);
        return new ResponseEntity<>(flights,HttpStatus.CREATED);
    }

    @PostMapping("/flights/search")
    public ResponseEntity<List<Flight>> searchFlights(@RequestBody SearchCriteriaDTO searchCriteria) {
        // Logic to search for flights based on the provided criteria
        List<Flight> flights = flightService.findByDepartureDate(
                searchCriteria.getDeparture(),
                searchCriteria.getArrival(),
                searchCriteria.getDate()
        );
        if (flights.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return new ResponseEntity<>(flights, HttpStatus.OK);
        }
    }

    @PostMapping("/reservation/book")
    public ResponseEntity<ReservationRequestDTO> bookReservation(@RequestBody ReservationRequestDTO reservationRequestdto) {
        ReservationRequestDTO dtos =  reservationService.bookReservation(reservationRequestdto);
        return new ResponseEntity<>(dtos,HttpStatus.CREATED);
    }

    // Get a flight by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable("id") long id) {
        try {
            Flight flight = flightService.getFlightById(id);
            return new ResponseEntity<>(flight, HttpStatus.OK);
        } catch (FlightNotFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    // Update an existing flight by its ID
    @PutMapping("/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable("id") long id,
                                               @RequestBody  Flight updatedFlight) {
        try {
            Flight flight = flightService.updateFlight(id, updatedFlight);
            return new ResponseEntity<>(flight, HttpStatus.OK);
        } catch (FlightNotFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    // Delete a flight by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFlight(@PathVariable("id") long id) {
        try {
            boolean isDeleted = flightService.deleteFlight(id);
            if (isDeleted) {
                return new ResponseEntity<>("Flight deleted successfully", HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>("Flight not found", HttpStatus.NOT_FOUND);
            }
        } catch (FlightNotFoundException ex) {
            return new ResponseEntity<>("Flight not found", HttpStatus.NOT_FOUND);
        }
    }
}
