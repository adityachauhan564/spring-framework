package springmvc.topic07_rest_controller_json;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import springmvc.topic04_service_and_dao_layers.User;
import springmvc.topic04_service_and_dao_layers.UserService;
import springmvc.topic06_exception_handling.UserNotFoundException;

import jakarta.validation.Valid;

/*
 * Key idea : @RestController = @Controller + @ResponseBody. The methods return DATA,
 *            and Jackson (a JSON library) turns it into JSON. No view, no JSP.
 *            It uses the same UserService as the web pages.
 *              GET  /api/users       -> 200 + list
 *              GET  /api/users/{id}  -> 200, or 404 (RestExceptionHandler)
 *              POST /api/users       -> 201 Created + Location header, 400 if invalid, 409 if taken
 *            Spring Boot (stage 03) builds REST APIs exactly like this, just without all the setup.
 * Try this : curl http://localhost:8080/springmvc/api/users
 *            curl -X POST -H "Content-Type: application/json" \
 *                 -d "{\"userName\":\"Asha\",\"email\":\"asha@example.com\",\"password\":\"secret123\"}" \
 *                 http://localhost:8080/springmvc/api/users
 */
@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponse> all() {
        return userService.findAll().stream().map(UserResponse::from).toList();
    }

    @GetMapping("/{id}")
    public UserResponse one(@PathVariable int id) {
        return userService.findById(id).map(UserResponse::from).orElseThrow(() -> new UserNotFoundException(id));
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody CreateUserRequest request) {
        if (userService.isEmailTaken(request.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "email already registered");
        }
        User user = userService.register(request.userName(), request.email(), request.password());
        return ResponseEntity.created(URI.create("/springmvc/api/users/" + user.getId())).body(UserResponse.from(user));
    }
}
