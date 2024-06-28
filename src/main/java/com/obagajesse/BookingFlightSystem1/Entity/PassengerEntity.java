package com.obagajesse.BookingFlightSystem1.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.obagajesse.BookingFlightSystem1.DTO.Booking;
import com.obagajesse.BookingFlightSystem1.Enum.ClassType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "passengers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PassengerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long bookingId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @Column(nullable = false,unique = true)
    private String passportNumber;

    @Column(nullable = false)
    private String nationality;

    @Column(nullable = false)
    private String seatNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ClassType classType;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private BookingEntity booking;

    public PassengerEntity(Long id, Long bookingId, String firstName, String lastName, LocalDate dateOfBirth, String passportNumber, String nationality, String seatNumber, ClassType classType, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.bookingId = bookingId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.passportNumber = passportNumber;
        this.nationality = nationality;
        this.seatNumber = seatNumber;
        this.classType = classType;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "PassengerEntity{" +
                "id=" + id +
                ", bookingId=" + bookingId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", passportNumber='" + passportNumber + '\'' +
                ", nationality='" + nationality + '\'' +
                ", seatNumber='" + seatNumber + '\'' +
                ", classType=" + classType +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
