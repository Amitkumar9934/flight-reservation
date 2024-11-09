package com.webapp.flight.payload;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public class SearchCriteriaDTO {

    private String departure;
    private String arrival;
    private String date;
    private PassengersDTO passengers;

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public PassengersDTO getPassengers() {
        return passengers;
    }

    public void setPassengers(PassengersDTO passengers) {
        this.passengers = passengers;
    }
}
