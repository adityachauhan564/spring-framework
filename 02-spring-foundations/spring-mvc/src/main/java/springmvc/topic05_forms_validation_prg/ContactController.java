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
 *            2. POST binds the fields into SignupForm (@ModelAttribute) and checks the
 *               annotations on it (@Valid). Problems land in BindingResult - it must come
 *               RIGHT AFTER the form parameter, or Spring throws instead.
 *            3. Errors -> show the SAME form again, with messages and the typed values kept.
 *            4. Success -> REDIRECT to a GET page. Refreshing that page can't re-submit the form
 *               (without the redirect, F5 would register the user twice). Flash attributes
 *               carry data across the redirect exactly once.
 * Try this : submit an empty form, then a short password, then the same email twice.
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
            return "contact";                              // same page, with the error messages
        }

        User user = userService.register(form.getUserName(), form.getEmail(), form.getPassword());
        redirect.addFlashAttribute("user", user);
        return "redirect:/success";                        // Post/Redirect/Get
    }

    @GetMapping("/success")
    public String success(Model model) {
        if (!model.containsAttribute("user")) {
            return "redirect:/contact";                    // opened directly or refreshed: nothing to show
        }
        return "success";
    }
}
