package springmvc.topic05_forms_validation_prg;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/*
 * What the HTML form sends. It is kept separate from the User entity
 * (this is called a "form object" or DTO - Data Transfer Object), because:
 * it carries the raw password, which must never be stored, and it has its own validation rules.
 * The field names must match the form's input names (email, userName, password).
 * It is a class with getters/setters (not a record), so the JSP form tags can read it.
 */
public class SignupForm {

    @NotBlank(message = "please enter a user name")
    @Size(max = 50, message = "at most 50 characters")
    private String userName;

    @NotBlank(message = "please enter an email")
    @Email(message = "that is not a valid email address")
    private String email;

    @NotBlank(message = "please choose a password")
    @Size(min = 8, message = "at least 8 characters")
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
