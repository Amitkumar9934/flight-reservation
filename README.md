# Flight Reservation System

## Project Overview
The **Flight Reservation System** is a web-based application that allows users to search for flights, book reservations, and manage flight data. This system includes functionalities for flight creation, searching for available flights based on specific criteria, booking reservations, and CRUD operations on flight data.

## Technologies Used
- **Java 8+**
- **Spring Boot**
- **Spring Data JPA**
- **RESTful APIs**
- **MySQL/PostgreSQL** (or any preferred RDBMS)
- **Angular** (for the frontend)
- **Spring Security** (optional, for securing endpoints)

## Features
- **Add a Flight**: Create a new flight with details such as flight number, departure, arrival, and more.
- **Search Flights**: Search for available flights based on criteria like departure and arrival locations and departure date.
- **Book a Reservation**: Reserve a seat on a flight by providing passenger details and flight ID.
- **Get Flight by ID**: Retrieve details of a specific flight using its unique ID.
- **Update a Flight**: Modify the details of an existing flight.
- **Delete a Flight**: Remove a flight record from the system.

## API Endpoints
### Flight Management
- **POST /api/addflight**: Add a new flight.
- **POST /api/flights/search**: Search for flights based on criteria.
- **GET /api/{id}**: Retrieve flight details by ID.
- **PUT /api/{id}**: Update an existing flight by ID.
- **DELETE /api/{id}**: Delete a flight by ID.

### Reservation Management
- **POST /api/reservation/book**: Book a flight reservation.

## Installation and Setup
1. **Clone the repository**:
   ```bash
   git clone (https://github.com/Amitkumar9934/flight-reservation.git)
   cd flight
2.  Configure the Database: Update the application.    properties file with your database configuration:

spring.datasource.url=jdbc:mysql://localhost:3306/myfightdb
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

3. Build and Run the Application:

mvn clean install
mvn spring-boot:run

4.Access the API: The application runs on http://localhost:8080/api.

Add a Flight
POST /api/addflight
{
  "flightNumber": "FL123",
  "departure": "Ranchi",
  "arrival": "Patna",
  "date": "2024-04-27",
  "availability": "Yes"
}

Book a Reservation
POST /api/reservation/book
{
   "flightId": "ABY896",
  "passengerslist": [
    {
      "name": "Nan Doe",
      "age": 30,
      "email": "nan.doe@example.com",
      "phone": "9234567790"
    },
  ]
}

Error Handling
The system handles exceptions such as FlightNotFoundException and returns appropriate HTTP status codes with descriptive messages.
