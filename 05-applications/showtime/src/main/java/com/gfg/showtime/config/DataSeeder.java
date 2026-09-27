package com.gfg.showtime.config;

import java.time.LocalDate;
import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gfg.showtime.enums.Genre;
import com.gfg.showtime.enums.Role;
import com.gfg.showtime.repository.UserRepository;
import com.gfg.showtime.resource.MovieResource;
import com.gfg.showtime.resource.ShowResource;
import com.gfg.showtime.resource.SignupRequest;
import com.gfg.showtime.resource.TheaterResource;
import com.gfg.showtime.service.MovieService;
import com.gfg.showtime.service.ShowService;
import com.gfg.showtime.service.TheaterService;
import com.gfg.showtime.service.UserService;

/*
 * Sample data at startup, created through the normal services (so passwords are hashed and show seats
 * are generated). Runs only on an empty database, so a MySQL database isn't seeded twice.
 * These accounts are for local demos only.
 */
@Configuration
public class DataSeeder {

    private static final Logger log = LoggerFactory.getLogger(DataSeeder.class);

    @Bean
    ApplicationRunner seed(UserRepository users, UserService userService, TheaterService theaterService,
            MovieService movieService, ShowService showService,
            @Value("${showtime.seed.admin-password}") String adminPassword) {
        return args -> {
            if (users.count() > 0) return;

            userService.create(new SignupRequest("Admin", adminPassword, "9000000000", "admin@showtime.local"), Role.ADMIN);
            userService.create(new SignupRequest("Asha", "password123", "9876543210", "asha@example.com"), Role.USER);

            long theater = theaterService.addTheater(new TheaterResource(null, "PVR Phoenix", "Mumbai", "Lower Parel")).id();
            long inception = movieService.addMovie(new MovieResource(null, "Inception", Genre.SCI_FI, null, null)).id();
            long idiots = movieService.addMovie(new MovieResource(null, "3 Idiots", Genre.COMEDY, null, null)).id();
            movieService.addMovie(new MovieResource(null, "Interstellar", Genre.SCI_FI, null, null));

            LocalDate tomorrow = LocalDate.now().plusDays(1);
            showService.addShow(new ShowResource(null, tomorrow.atTime(LocalTime.of(18, 0)), inception, theater, null, null, null, null, null, null));
            showService.addShow(new ShowResource(null, tomorrow.atTime(LocalTime.of(21, 0)), idiots, theater, null, null, null, null, null, null));

            log.info("Sample data added. Logins: admin@showtime.local (ADMIN), asha@example.com / password123 (USER)");
        };
    }
}
