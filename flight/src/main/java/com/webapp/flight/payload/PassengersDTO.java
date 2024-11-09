package com.webapp.flight.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassengersDTO {

    private int adults;
    private int children;
    private int infants;
}
