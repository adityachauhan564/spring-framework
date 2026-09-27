package com.udemy.web.services.restful_web_services.topic03_crud_resource;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

/*
 * Topic    : A complete CRUD resource with the right HTTP status codes
 * Key idea : a REST resource is a noun (/users); the HTTP METHOD is the verb.
 *   GET    /users       200 + list
 *   GET    /users/{id}  200, or 404 if missing
 *   POST   /users       201 Created + Location header pointing at the new user
 *   PUT    /users/{id}  200 + the replaced user, or 404
 *   DELETE /users/{id}  204 No Content, or 404
 *   @Valid (topic04) rejects bad input with 400 before the method even runs.
 * Try this : curl -i -X DELETE localhost:8080/users/2   (topic09: needs a login - see its notes)
 */
@RestController
@RequestMapping("/users")
public class UserResource {

    private final UserStore store;

    public UserResource(UserStore store) {
        this.store = store;
    }

    @GetMapping
    public List<User> retrieveAllUsers() {
        return store.findAll();
    }

    @GetMapping("/{id}")
    public User retrieveUser(@PathVariable int id) {
        return store.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User saved = store.save(user);
        // build /users/{id} from the CURRENT request, so host and port are never hard-coded
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.id())
                .toUri();
        return ResponseEntity.created(location).body(saved);
    }

    @PutMapping("/{id}")
    public User replaceUser(@PathVariable int id, @Valid @RequestBody User user) {
        return store.replace(id, user).orElseThrow(() -> new UserNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        if (!store.delete(id)) {
            throw new UserNotFoundException(id);
        }
        return ResponseEntity.noContent().build();
    }
}
