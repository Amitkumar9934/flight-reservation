package com.webapp.flight.service.impl;

import com.webapp.flight.entity.Passenger;
import com.webapp.flight.entity.Reservation;
import com.webapp.flight.payload.PassengerlistDTO;
import com.webapp.flight.payload.ReservationRequestDTO;
import com.webapp.flight.repository.ReservationRepository;
import com.webapp.flight.service.ReservationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public ReservationRequestDTO bookReservation(ReservationRequestDTO reservationRequestdto) {

        Reservation reservation = new Reservation();
        reservation.setFlightId(reservationRequestdto.getFlightId());

        List<PassengerlistDTO> passengerlistDTOs = reservationRequestdto.getPassengerslist();
        List<Passenger> passengers = new ArrayList<>();
//        for (PassengerlistDTO passengerlistDTO : passengerlistDTOs) {
//            Passenger passenger = new Passenger();
//            passenger.setName(passengerlistDTO.getName());
//            passenger.setAge(passengerlistDTO.getAge());
//            passenger.setEmail(passengerlistDTO.getEmail());
//            passenger.setPhone(passengerlistDTO.getPhone());
//            passenger.setReservation(reservation);
//            passengers.add(passenger);
//        }
        passengerlistDTOs.stream().forEach(passengerlistDTO -> {
            // Code for creating Passenger objects and setting properties
            Passenger passenger = new Passenger();
            passenger.setName(passengerlistDTO.getName());
            passenger.setAge(passengerlistDTO.getAge());
            passenger.setEmail(passengerlistDTO.getEmail());
            passenger.setPhone(passengerlistDTO.getPhone());
            passenger.setReservation(reservation);
            passengers.add(passenger);
        });

        reservation.setPassengers(passengers);

        // Save the reservation
        Reservation savedReservation = reservationRepository.save(reservation);

        // Construct and return the response DTO
        ReservationRequestDTO responseDTO = new ReservationRequestDTO();
        responseDTO.setFlightId(savedReservation.getFlightId());
        List<PassengerlistDTO> savedPassengerDTOs = new ArrayList<>();
        for (Passenger passenger : savedReservation.getPassengers()) {
            PassengerlistDTO passengerDTO = new PassengerlistDTO();
            passengerDTO.setName(passenger.getName());
            passengerDTO.setAge(passenger.getAge());
            passengerDTO.setEmail(passenger.getEmail());
            passengerDTO.setPhone(passenger.getPhone());
            savedPassengerDTOs.add(passengerDTO);
        }
        responseDTO.setPassengerslist(savedPassengerDTOs);

        return responseDTO;
    }
    }
