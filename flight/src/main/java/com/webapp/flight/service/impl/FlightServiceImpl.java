package com.webapp.flight.service.impl;

import com.webapp.flight.entity.Flight;
import com.webapp.flight.exception.FlightNotFoundException;
import com.webapp.flight.repository.FlightRepository;
import com.webapp.flight.service.FlightService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService {

    private FlightRepository flightRepository;

    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }


    @Override
    public List<Flight> findByDepartureDate(String departure, String arrival, String date) {
        return flightRepository.findByDepartureAndArrivalAndDate(departure, arrival, date);
    }

    @Override
    public Flight addFlight(Flight flight) {
        Flight flights = flightRepository.save(flight);
        return flights;
    }
    @Override
    public Flight getFlightById(long id) {
        // Use findById to get the flight by its ID
        Optional<Flight> flight = flightRepository.findById(id);

        // Check if the flight is present, and if not, throw an exception or return null
        if (flight.isPresent()) {
            return flight.get();
        } else {
            // Handle the case when the flight is not found (optional: throw exception)
            throw new FlightNotFoundException("Flight not found with ID: " + id);
        }
    }

    @Override
    public Flight updateFlight(long id, Flight updatedFlight) {
        // Step 1: Find the flight by its ID
        Optional<Flight> existingFlightOptional = flightRepository.findById(id);

        // Step 2: Check if the flight exists
        if (existingFlightOptional.isPresent()) {
            Flight existingFlight = existingFlightOptional.get();

            // Step 3: Update the existing flight's fields with the new values
            existingFlight.setFlightNumber(updatedFlight.getFlightNumber());
            existingFlight.setDeparture(updatedFlight.getDeparture());
            existingFlight.setArrival(updatedFlight.getArrival());
            // (Update any other fields as necessary)

            // Step 4: Save the updated flight and return it
            return flightRepository.save(existingFlight);
        } else {
            // If the flight is not found, you could throw an exception or return null
            throw new FlightNotFoundException("Flight not found with ID: " + id);
        }
    }

    @Override
    public boolean deleteFlight(long id) {
        Optional<Flight> flight = flightRepository.findById(id);

        if (flight.isPresent()) {
            flightRepository.deleteById(id);
            return true;
        } else {
            throw new FlightNotFoundException("Flight not found with ID: " + id);
        }
    }



}
