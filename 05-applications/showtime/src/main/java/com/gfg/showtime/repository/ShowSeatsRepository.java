package com.gfg.showtime.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gfg.showtime.domain.ShowSeat;

public interface ShowSeatsRepository extends JpaRepository<ShowSeat, Long> {
}
