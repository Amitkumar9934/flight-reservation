package com.webapp.flight;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.webapp.flight.entity.Flight;
import com.webapp.flight.exception.FlightNotFoundException;
import com.webapp.flight.repository.FlightRepository;
import com.webapp.flight.service.impl.FlightServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class FlightApplicationTests {

	@Mock
	private FlightRepository flightRepository;

	@InjectMocks
	private FlightServiceImpl flightService; // Ensure this matches your actual service class

	@Test
	public void testAddFlight() {
		// Arrange
		Flight flight = new Flight();
		flight.setId(1L);
		flight.setFlightNumber("AI202");
		flight.setDeparture("New York");
		flight.setArrival("Los Angeles");

		// Mock behavior
		when(flightRepository.save(any(Flight.class))).thenReturn(flight);

		// Act
		Flight savedFlight = flightService.addFlight(flight);

		// Assert
		assertEquals(flight.getId(), savedFlight.getId());
		assertEquals(flight.getFlightNumber(), savedFlight.getFlightNumber());
		assertEquals(flight.getDeparture(), savedFlight.getDeparture());
		assertEquals(flight.getArrival(), savedFlight.getArrival());
	}
	// Test Case for getting a flight by ID
	@Test
	public void testGetFlightById() {
		Flight flight = new Flight();
		flight.setId(1L);
		flight.setFlightNumber("AI202");
		flight.setDeparture("New York");
		flight.setArrival("Los Angeles");

		// Mock the behavior of flightRepository.findById()
		when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));

		Flight foundFlight = flightService.getFlightById(1L);

		assertEquals(flight.getId(), foundFlight.getId());
		assertEquals(flight.getFlightNumber(), foundFlight.getFlightNumber());
		assertEquals(flight.getDeparture(), foundFlight.getDeparture());
		assertEquals(flight.getArrival(), foundFlight.getArrival());
	}
	//Test Case for update
	@Test
	public void testUpdateFlight() {
		Flight existingFlight = new Flight();
		existingFlight.setId(1L);
		existingFlight.setFlightNumber("AI202");
		existingFlight.setDeparture("New York");
		existingFlight.setArrival("Los Angeles");

		Flight updatedFlight = new Flight();
		updatedFlight.setId(1L);
		updatedFlight.setFlightNumber("AI203");
		updatedFlight.setDeparture("Boston");
		updatedFlight.setArrival("Chicago");

		// Mock the behavior of flightRepository.findById()
		when(flightRepository.findById(1L)).thenReturn(Optional.of(existingFlight));
		when(flightRepository.save(any(Flight.class))).thenReturn(updatedFlight);

		Flight result = flightService.updateFlight(1L, updatedFlight);

		assertEquals(updatedFlight.getFlightNumber(), result.getFlightNumber());
		assertEquals(updatedFlight.getDeparture(), result.getDeparture());
		assertEquals(updatedFlight.getArrival(), result.getArrival());
	}
	// Test Case for deleting a flight that doesn't exist
	@Test
	public void testDeleteFlightNotFound() {
		// Arrange: Mock flightRepository to return empty Optional for a non-existent flight
		long flightId = 1L;
		when(flightRepository.findById(flightId)).thenReturn(Optional.empty());

		// Act and Assert: Call deleteFlight and assert that a FlightNotFoundException is thrown
		assertThrows(FlightNotFoundException.class, () -> flightService.deleteFlight(flightId));

		// Verify: Ensure deleteById is not called since the flight doesn't exist
		verify(flightRepository, never()).deleteById(flightId);
	}

	@Test
	public void testDeleteFlightSuccess() {
		// Arrange: Mock flightRepository to return a flight object when the flight exists
		long flightId = 1L;
		Flight flight = new Flight();
		flight.setId(flightId);
		when(flightRepository.findById(flightId)).thenReturn(Optional.of(flight));

		// Act: Call deleteFlight
		boolean result = flightService.deleteFlight(flightId);

		// Assert: Check if the flight was deleted successfully
		assertEquals(true, result);

		// Verify: Ensure deleteById was called with the correct flightId
		verify(flightRepository).deleteById(flightId);
	}
}