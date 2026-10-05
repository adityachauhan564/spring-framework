package springmvc.topic05_forms_validation_prg;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import springmvc.topic04_service_and_dao_layers.User;
import springmvc.topic04_service_and_dao_layers.UserService;

import jakarta.validation.Valid;

/*
 * Topic    : Forms - binding, validation and Post/Redirect/Get
 * Key idea : 1. GET shows an empty form.
 *            2. POST copies the form fields into a SignupForm (@ModelAttribute), and checks
 *               the rules written on it (@Valid). Any problems go into BindingResult.
 *               BindingResult must come RIGHT AFTER the form parameter, or Spring throws an exception instead.
 *            3. Errors -> show the SAME form again, with the messages, and keep what the user typed.
 *            4. Success -> REDIRECT to a GET page. Now refreshing that page cannot submit the form again
 *               (without the redirect, pressing F5 would register the user twice).
 *               Like a railway ticket counter giving you a printed receipt - showing the receipt
 *               again does not book a second ticket.
 *               Flash attributes carry data across the redirect, exactly once.
 * Try this : Submit an empty form, then a short password, then the same email twice.
 */
@Controller
public class ContactController {

    private final UserService userService;

    public ContactController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/contact")
    public String showForm(Model model) {
        model.addAttribute("form", new SignupForm());
        return "contact";
    }

    @PostMapping("/processform")
    public String handleForm(@Valid @ModelAttribute("form") SignupForm form,
                             BindingResult errors,
                             RedirectAttributes redirect) {
        if (!errors.hasFieldErrors("email") && userService.isEmailTaken(form.getEmail())) {
            errors.rejectValue("email", "duplicate", "this email is already registered");
        }
        if (errors.hasErrors()) {
            return "contact";                              // the same page again, now with the error messages
        }

        User user = userService.register(form.getUserName(), form.getEmail(), form.getPassword());
        redirect.addFlashAttribute("user", user);
        return "redirect:/success";                        // Post/Redirect/Get
    }

    @GetMapping("/success")
    public String success(Model model) {
        if (!model.containsAttribute("user")) {
            return "redirect:/contact";                    // opened directly or refreshed: there is nothing to show
        }
        return "success";
    }
}
