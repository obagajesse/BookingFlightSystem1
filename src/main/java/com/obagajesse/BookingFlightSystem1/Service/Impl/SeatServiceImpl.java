package com.obagajesse.BookingFlightSystem1.Service.Impl;

import com.obagajesse.BookingFlightSystem1.DTO.Seat;
import com.obagajesse.BookingFlightSystem1.Entity.SeatEntity;
import com.obagajesse.BookingFlightSystem1.Mapper.SeatMapper;
import com.obagajesse.BookingFlightSystem1.Repository.SeatRepository;
import com.obagajesse.BookingFlightSystem1.Service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Primary
public class SeatServiceImpl implements SeatService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SeatRepository seatRepository;

    public SeatServiceImpl(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    private final String flaskApiBaseUrl = "http://192.168.1.63:8000/api/admin/airplane/airplane/123/seats";

    public List<Seat> getSeatsByFlightId(Long flightId){
        String url = flaskApiBaseUrl + flightId + "/seats";
        Seat[] seats = restTemplate.getForObject(url,Seat[].class);
        return Arrays.stream(seats).collect(Collectors.toList());
    }

    @Override
    public Seat createSeat(Seat seat) {
        SeatEntity seatEntity = SeatMapper.mapToSeatEntity(seat);
        seatEntity.setAvailability(seat.getAvailability());
        seatEntity.setUpdatedAt(LocalDateTime.now());
        seatEntity.setCreatedAt(LocalDateTime.now());
        SeatEntity savedSeatEntity = seatRepository.save(seatEntity);
        return SeatMapper.mapToSeat(savedSeatEntity);
    }

    @Override
    public List<Seat> getAllSeats() {
        List<SeatEntity> seatEntities = seatRepository.findAll();
        return seatEntities.stream().map(SeatMapper::mapToSeat).collect(Collectors.toList());
    }

    @Override
    public Seat getSeatByNumber(String number) {
        return null;
    }

    @Override
    public Seat getSeatById(Long id){
        return seatRepository.findById(id).orElse(null);
    }

    @Override
    public Seat updateSeat(Seat seat){
        SeatEntity seatEntity = SeatMapper.mapToSeatEntity(seat);
        seatEntity.setAvailability(seat.getAvailability());
        seatEntity.setUpdatedAt(LocalDateTime.now());
        seatEntity.setCreatedAt(LocalDateTime.now());
        SeatEntity updatedSeatEntity = seatRepository.save(seatEntity);
        return SeatMapper.mapToSeat(updatedSeatEntity);
    }

    @Override
    public void deleteSeat(Long id){
        seatRepository.deleteById(id);
    }

    @Override
    public boolean checkSeatAvailability(Long flightId,String seatNumber){
        List<Seat> seats = getSeatsByFlightId(flightId);
        return seats.stream()
                .anyMatch(seat -> seat.getSeatNumber().equals(seatNumber) && seat.getAvailability());
    }

    @Override
    public Seat bookSeat(Long flightId,String seatNumber){
        List<Seat> seats = getSeatsByFlightId(flightId);
        Seat seatToBook = seats.stream()
                .filter(seat -> seat.getSeatNumber().equals(seatNumber) && seat.getAvailability())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Seat Not Available"));

        seatToBook.setAvailability(false);
        return updateSeat(seatToBook);
    }
}
