package com.webapp.flight.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassengerlistDTO {

    private String name;
    private int age;
    private String email;
    private String phone;
}
