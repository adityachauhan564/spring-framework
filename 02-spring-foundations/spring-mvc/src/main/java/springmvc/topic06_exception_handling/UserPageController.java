package springmvc.topic06_exception_handling;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import springmvc.topic04_service_and_dao_layers.UserService;

/*
 * The controller only THROWS. It has no try/catch and no error-page code.
 * PageExceptionHandler turns the exception into a proper 404 page.
 * Try this : Open /springmvc/users/999 and /springmvc/error-demo.
 */
@Controller
public class UserPageController {

    private final UserService userService;

    public UserPageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String allUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @GetMapping("/users/{id}")
    public String oneUser(@PathVariable int id, Model model) {
        model.addAttribute("user", userService.findById(id).orElseThrow(() -> new UserNotFoundException(id)));
        return "user";
    }

    // a bug on purpose, to show the catch-all handler at work
    @GetMapping("/error-demo")
    public String errorDemo() {
        throw new IllegalStateException("something broke inside the application");
    }
}
