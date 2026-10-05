package com.gfg.showtime;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import com.gfg.showtime.domain.Movie;
import com.gfg.showtime.domain.Show;
import com.gfg.showtime.domain.Theater;
import com.gfg.showtime.enums.Genre;
import com.gfg.showtime.repository.MovieRepository;
import com.gfg.showtime.repository.ShowRepository;
import com.gfg.showtime.repository.TheaterRepository;

/*
 * @DataJpaTest: only JPA (no web, no security, no DataSeeder), a fresh H2 database, and a rollback
 * (undo) after each test. The data is built here, so each query is checked against rows we know exactly.
 */
@DataJpaTest
class RepositoryQueryTest {

    @Autowired MovieRepository movies;
    @Autowired TheaterRepository theaters;
    @Autowired ShowRepository shows;

    @BeforeEach
    void data() {
        Theater mumbai = theaters.save(Theater.builder().name("PVR").city("Mumbai").address("a").build());
        Theater pune = theaters.save(Theater.builder().name("INOX").city("Pune").address("b").build());
        Movie dune = movies.save(Movie.builder().title("Dune").genre(Genre.SCI_FI).rating(4.5).build());
        Movie up = movies.save(Movie.builder().title("Up").genre(Genre.COMEDY).rating(4.0).build());
        for (int i = 1; i <= 6; i++) {
            movies.save(Movie.builder().title("SciFi " + i).genre(Genre.SCI_FI).rating(i / 2.0).build());
        }
        movies.save(Movie.builder().title("Unrated").genre(Genre.SCI_FI).build());

        LocalDateTime evening = LocalDateTime.now().plusDays(1);
        shows.save(Show.builder().movie(dune).theater(mumbai).showTime(evening).build());
        shows.save(Show.builder().movie(up).theater(mumbai).showTime(evening.plusHours(3)).build());
        shows.save(Show.builder().movie(dune).theater(pune).showTime(evening).build());
    }

    @Test
    void searchTreatsNullFiltersAsNotGiven() {
        assertThat(shows.search("mumbai", null, null)).hasSize(2);
        assertThat(shows.search("Mumbai", "Dune", null)).hasSize(1);
        assertThat(shows.search("Pune", null, "INOX")).hasSize(1);
        assertThat(shows.search("Delhi", null, null)).isEmpty();
    }

    @Test
    void topFiveIsTheBestRatedOfTheGenreAndSkipsUnrated() {
        assertThat(movies.findTop5ByGenreAndRatingNotNullOrderByRatingDesc(Genre.SCI_FI))
                .extracting(Movie::getTitle)
                .containsExactly("Dune", "SciFi 6", "SciFi 5", "SciFi 4", "SciFi 3");
    }
}
