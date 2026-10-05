package springmvc.topic07_rest_controller_json;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/* What the API RECEIVES as a JSON body. It has the same rules and messages as the HTML form (topic05). */
public record CreateUserRequest(
        @NotBlank(message = "please enter a user name") @Size(max = 50, message = "at most 50 characters") String userName,
        @NotBlank(message = "please enter an email") @Email(message = "that is not a valid email address") String email,
        @NotBlank(message = "please choose a password") @Size(min = 8, message = "at least 8 characters") String password) {
}
