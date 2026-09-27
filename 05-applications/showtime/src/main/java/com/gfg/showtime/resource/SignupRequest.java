package com.gfg.showtime.resource;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// No role field: everyone who signs up is a USER. Letting the client send "role":"ADMIN" would be a hole.
public record SignupRequest(
        @NotBlank(message = "Name is mandatory") String name,
        @NotBlank @Size(min = 8, message = "Password needs at least 8 characters") String password,
        @NotBlank(message = "Mobile is mandatory") String mobile,
        @NotBlank @Email(message = "Email is not valid") String email) {
}
