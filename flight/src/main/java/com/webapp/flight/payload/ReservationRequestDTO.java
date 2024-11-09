package com.webapp.flight.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservationRequestDTO {

    private String flightId;
    private List<PassengerlistDTO> passengerslist;
}
