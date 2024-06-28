package com.obagajesse.BookingFlightSystem1.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.obagajesse.BookingFlightSystem1.DTO.Ticket;
import com.obagajesse.BookingFlightSystem1.Enum.ClassType;
import com.obagajesse.BookingFlightSystem1.Enum.TicketStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketEntity extends Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long passengerId;

    @Column(nullable = false)
    private Long bookingId;

    @Column(nullable = false,unique = true)
    private String ticketNumber;

    @Column(nullable = false)
    private Long flightId;

    @Column(nullable = false)
    private String seatNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ClassType classType;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime issueDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private BookingEntity booking;

    public TicketEntity(Long id, Long bookingId, Long passengerId, String ticketNumber, Long flightId, String seatNumber, ClassType classType, LocalDateTime issueDate, TicketStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.bookingId = bookingId;
        this.passengerId = passengerId;
        this.ticketNumber = ticketNumber;
        this.flightId = flightId;
        this.seatNumber = seatNumber;
        this.classType = classType;
        this.issueDate = issueDate;
        this.status = status;
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
        return "TicketEntity{" +
                "id=" + id +
                ", passengerId=" + passengerId +
                ", bookingId=" + bookingId +
                ", ticketNumber='" + ticketNumber + '\'' +
                ", flightId=" + flightId +
                ", seatNumber='" + seatNumber + '\'' +
                ", classType=" + classType +
                ", issueDate=" + issueDate +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public void setStatus() {
    }
}
