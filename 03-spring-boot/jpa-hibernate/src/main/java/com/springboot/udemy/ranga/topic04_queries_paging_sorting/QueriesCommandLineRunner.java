package com.springboot.udemy.ranga.topic04_queries_paging_sorting;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.springboot.udemy.ranga.topic02_jpa_entity_manager.Course;
import com.springboot.udemy.ranga.topic03_spring_data_jpa.CourseSpringDataRepository;

/*
 * Topic    : Query methods, @Query, paging and sorting
 * Key idea : the repository interface in topic03 declares the queries; this runner calls them.
 *            Paging: PageRequest.of(pageNumber, pageSize, sort) - page numbers start at 0.
 *            A Page knows its content AND the totals, so a UI can show "page 1 of 3".
 * Try this : add findByAuthorOrderByNameDesc(String author) to the repository and call it here.
 */
@Component
@Order(4)
public class QueriesCommandLineRunner implements CommandLineRunner {

    private final CourseSpringDataRepository repository;

    public QueriesCommandLineRunner(CourseSpringDataRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        System.out.println("\n=== topic04: query methods, @Query, paging and sorting ===");
        System.out.println("findByAuthor(in28minutes): " + names(repository.findByAuthor("in28minutes")));
        System.out.println("findByNameContainingIgnoreCase(learn): " + names(repository.findByNameContainingIgnoreCase("learn")));
        System.out.println("countByAuthor(in28minutes): " + repository.countByAuthor("in28minutes"));
        System.out.println("@Query all except in28minutes: " + names(repository.findAllExceptAuthor("in28minutes")));

        System.out.println("findAll sorted by name desc: " + names(repository.findAll(Sort.by("name").descending())));
        Page<Course> page = repository.findByAuthor("in28minutes", PageRequest.of(0, 2, Sort.by("name")));
        System.out.println("page 0 (size 2) of in28minutes courses: " + names(page.getContent())
                + "  -> " + page.getTotalElements() + " total, " + page.getTotalPages() + " pages");
    }

    private static String names(Iterable<Course> courses) {
        StringBuilder out = new StringBuilder("[");
        courses.forEach(course -> out.append(out.length() > 1 ? ", " : "").append(course.getName()));
        return out.append("]").toString();
    }
}
