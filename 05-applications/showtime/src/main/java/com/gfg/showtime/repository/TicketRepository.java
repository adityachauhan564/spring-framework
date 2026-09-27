package com.gfg.showtime.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gfg.showtime.domain.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
