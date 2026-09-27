package com.gfg.showtime.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gfg.showtime.domain.Theater;

public interface TheaterRepository extends JpaRepository<Theater, Long> {
}
