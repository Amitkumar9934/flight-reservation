package com.webapp.flight.service;

import com.webapp.flight.payload.ReservationRequestDTO;

public interface ReservationService {
    ReservationRequestDTO bookReservation(ReservationRequestDTO reservationRequestdto);
}
