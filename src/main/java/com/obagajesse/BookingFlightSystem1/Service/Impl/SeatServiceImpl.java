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

@Service
@Primary
public class SeatServiceImpl implements SeatService {

    @Autowired
    private RestTemplate restTemplate;

    private final String flaskApiBaseUrl = "http://localhost:5000/api/seats";

    public Seat[] getAllSeats(){
        return restTemplate.getForObject(flaskApiBaseUrl, Seat[].class);
    }

    public Seat getSeatByNumber(String seatNumber){
        return restTemplate.getForObject(flaskApiBaseUrl + "/" + seatNumber, Seat.class);
    }


    private final SeatRepository seatRepository;

    @Autowired
    public SeatServiceImpl(SeatRepository seatRepository){
        this.seatRepository = seatRepository;
    }

    @Override
    public Seat createSeat(Seat seat){
        SeatEntity seatEntity = SeatMapper.mapToSeatEntity(seat);
        seatEntity.setAvailability(seat.getAvailability());
        seatEntity.setUpdatedAt(LocalDateTime.now());
        seatEntity.setCreatedAt(LocalDateTime.now());
        SeatEntity savedSeatEntity = seatRepository.save(seatEntity);
        return SeatMapper.mapToSeat(savedSeatEntity);
    }

//    @Override
//    public List<Seat> getAllSeats(){
//        List<SeatEntity> seatEntities = seatRepository.findAll();
//        return seatEntities.stream().map(SeatMapper::mapToSeat).toList();
//    }

    @Override
    public Seat getSeatById(Long id){
        return seatRepository.findById(id).orElse(null);
    }

    @Override
    public Seat updateSeat(Seat seat){
        SeatEntity seatEntity = SeatMapper.mapToSeatEntity(seat);
        seatEntity.setAvailability(seat.getAvailability());
        seatEntity.setUpdatedAt(LocalDateTime.now());
        SeatEntity updatedSeatEntity = seatRepository.save(seatEntity);
        return SeatMapper.mapToSeat(updatedSeatEntity);
    }

    @Override
    public void deleteSeat(Long id){
        seatRepository.deleteById(id);
    }
}
