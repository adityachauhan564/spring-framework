package com.udemy.web.services.restful_web_services.topic03_crud_resource;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Component;

/*
 * An in-memory store that stands in for a database (the books API does the real thing with JPA).
 * The old version used a static ArrayList and a static int counter. Two requests at the same
 * time could get the same id, or break the list. Every request runs on its own thread,
 * so anything shared must be thread-safe:
 *   ConcurrentHashMap - safe when many threads read and write at the same time
 *   AtomicInteger     - incrementAndGet() never gives out the same number twice
 *                       (like a token machine at a bank - two people never get the same token)
 * And no 'static': the store is a singleton BEAN, so Spring already shares one object.
 */
@Component
public class UserStore {

    private final Map<Integer, User> users = new ConcurrentHashMap<>();
    private final AtomicInteger lastId = new AtomicInteger();

    public UserStore() {
        save(new User(null, "Raman", LocalDate.now().minusYears(29)));
        save(new User(null, "Padan", LocalDate.now().minusYears(25)));
        save(new User(null, "Rajan", LocalDate.now().minusYears(21)));
    }

    public List<User> findAll() {
        return users.values().stream().sorted((a, b) -> a.id().compareTo(b.id())).toList();
    }

    public Optional<User> findById(int id) {
        return Optional.ofNullable(users.get(id));
    }

    public User save(User user) {
        User saved = user.withId(lastId.incrementAndGet());
        users.put(saved.id(), saved);
        return saved;
    }

    public Optional<User> replace(int id, User user) {
        return Optional.ofNullable(users.computeIfPresent(id, (key, old) -> user.withId(id)));
    }

    public boolean delete(int id) {
        return users.remove(id) != null;
    }
}
