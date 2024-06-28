package com.obagajesse.BookingFlightSystem1.DTO;

import com.obagajesse.BookingFlightSystem1.Enum.ClassType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Passenger {

    private Long id;
    private Long bookingId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String passportNumber;
    private String nationality;
    private String seatNumber;
    private ClassType classType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

//    public Passenger(Long id, Long bookingId, String firstName, String lastName, LocalDateTime dateOfBirth, String passportNumber, String nationality, String seatNumber, ClassType classType, LocalDateTime createdAt, LocalDateTime updatedAt) {
//
//        this.id = id;
//        this.bookingId = bookingId;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.dateOfBirth = dateOfBirth;
//        this.passportNumber = passportNumber;
//        this.nationality = nationality;
//        this.seatNumber = seatNumber;
//        this.classType = classType;
//        this.createdAt = createdAt;
//        this.updatedAt = updatedAt;
//    }

//    public Passenger(Long id, Long bookingId, String firstName,String lastName ,LocalDate dateOfBirth, String passportNumber, String nationality, String seatNumber, ClassType classType, LocalDateTime createdAt, LocalDateTime updatedAt) {
//        this.id = id;
//        this.bookingId = bookingId;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.dateOfBirth = dateOfBirth;
//        this.passportNumber = passportNumber;
//        this.nationality = nationality;
//        this.seatNumber = seatNumber;
//        this.classType = classType;
//        this.createdAt = createdAt;
//        this.updatedAt = updatedAt;
//    }
}
