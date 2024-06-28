package com.obagajesse.BookingFlightSystem1.Service.Impl;

import com.obagajesse.BookingFlightSystem1.DTO.Passenger;
import com.obagajesse.BookingFlightSystem1.Entity.PassengerEntity;
import com.obagajesse.BookingFlightSystem1.Mapper.PassengerMapper;
import com.obagajesse.BookingFlightSystem1.Repository.PassengerRepository;
import com.obagajesse.BookingFlightSystem1.Service.PassengerService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;

    public PassengerServiceImpl(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    @Override
    public Passenger createPassenger(Passenger passenger){
        PassengerEntity passengerEntity = PassengerMapper.mapToPassengerEntity(passenger);
        passengerEntity.setUpdatedAt(LocalDateTime.now());
        passengerEntity.setCreatedAt(LocalDateTime.now());
        PassengerEntity savedPassengerEntity = passengerRepository.save(passengerEntity);
        return PassengerMapper.mapToPassenger(savedPassengerEntity);
    }

    @Override
    public List<Passenger> getAllPassengers(){
        List<PassengerEntity> passengerEntities = passengerRepository.findAll();
        return passengerEntities.stream().map(PassengerMapper::mapToPassenger).toList();
    }

    @Override
    public Passenger getPassengerById(Long id){
        return passengerRepository.findById(id).map(PassengerMapper::mapToPassenger).orElse(null);
    }

    @Override
    public Passenger updatePassenger(Passenger passenger){
        PassengerEntity passengerEntity = PassengerMapper.mapToPassengerEntity(passenger);
        passengerEntity.setUpdatedAt(LocalDateTime.now());
        passengerEntity.setCreatedAt(LocalDateTime.now());
        PassengerEntity updatedPassengerEntity = passengerRepository.save(passengerEntity);
        return PassengerMapper.mapToPassenger(updatedPassengerEntity);
    }

    @Override
    public void deletePassenger(Long id){
        passengerRepository.deleteById(id);
    }
}
