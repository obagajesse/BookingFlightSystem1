package com.obagajesse.BookingFlightSystem1.Service.Impl;

import com.obagajesse.BookingFlightSystem1.DTO.Ticket;
import com.obagajesse.BookingFlightSystem1.Entity.TicketEntity;
import com.obagajesse.BookingFlightSystem1.Enum.ClassType;
import com.obagajesse.BookingFlightSystem1.Mapper.TicketMapper;
import com.obagajesse.BookingFlightSystem1.Repository.TicketRepository;
import com.obagajesse.BookingFlightSystem1.Service.TicketService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TicketServiceImpl implements TicketService {

    private TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket createTicket(Ticket ticket){
        TicketEntity ticketEntity = TicketMapper.mapToTicketEntity(ticket);
        ticketEntity.setStatus();
        ticketEntity.setCreatedAt(LocalDateTime.now());
        ticketEntity.setTicketNumber(UUID.randomUUID().toString());
        ticketEntity.setIssueDate(LocalDateTime.now());
        TicketEntity savedTicketEntity = ticketRepository.save(ticketEntity);
        return TicketMapper.mapToTicket(savedTicketEntity);
    }

    @Override
    public List<Ticket> getAllTickets(){
        List<TicketEntity> ticketEntities = ticketRepository.findAll();
        return ticketEntities.stream().map(TicketMapper::mapToTicket).toList();
    }

    @Override
    public Ticket getTicketById(Long id){
        return ticketRepository.findById(id).orElse(null);
    }

    @Override
    public Ticket updateTicket(Ticket ticket){
        TicketEntity ticketEntity = TicketMapper.mapToTicketEntity(ticket);
        ticketEntity.setUpdatedAt(LocalDateTime.now());
        ticketEntity.setTicketNumber(UUID.randomUUID().toString());
        TicketEntity updatedTicketEntity = ticketRepository.save(ticketEntity);
        return TicketMapper.mapToTicket(updatedTicketEntity);
    }

    @Override
    public void deleteTicket(Long id){
        ticketRepository.deleteById(id);
    }
}
