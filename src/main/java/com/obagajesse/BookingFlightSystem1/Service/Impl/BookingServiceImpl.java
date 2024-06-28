package com.obagajesse.BookingFlightSystem1.Service.Impl;

import com.obagajesse.BookingFlightSystem1.DTO.Booking;
import com.obagajesse.BookingFlightSystem1.Entity.BookingEntity;
import com.obagajesse.BookingFlightSystem1.Mapper.BookingMapper;
import com.obagajesse.BookingFlightSystem1.Repository.BookingRepository;
import com.obagajesse.BookingFlightSystem1.Service.BookingService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Booking createBooking(Booking booking) {
        BookingEntity bookingEntity = BookingMapper.mapToBookingEntity(booking);
        bookingEntity.setUpdatedAt(LocalDateTime.now()); // Ensure updatedAt is set
        bookingEntity.setCreatedAt(LocalDateTime.now()); // Ensure createdAt is set if it's a new booking
        BookingEntity savedBookingEntity = bookingRepository.save(bookingEntity);
        return BookingMapper.mapToBooking(savedBookingEntity);
    }

    @Override
    public List<Booking> getAllBookings() {
        List<BookingEntity> bookingEntities = bookingRepository.findAll();
        return bookingEntities.stream().map(BookingMapper::mapToBooking).toList();
    }

    @Override
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).map(BookingMapper::mapToBooking).orElse(null);
    }

    @Override
    public Booking updateBooking(Booking booking) {
        BookingEntity bookingEntity = BookingMapper.mapToBookingEntity(booking);
        bookingEntity.setUpdatedAt(LocalDateTime.now());
        BookingEntity updatedBookingEntity = bookingRepository.save(bookingEntity);
        return BookingMapper.mapToBooking(updatedBookingEntity);
    }

    @Override
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}
