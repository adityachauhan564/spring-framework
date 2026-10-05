package com.gfg.showtime.resource;

import jakarta.validation.constraints.NotBlank;

public record TheaterResource(
        Long id,               // Long (a wrapper), not long: Jackson 3 rejects a request that leaves out a primitive field
        @NotBlank(message = "Name is mandatory") String name,
        @NotBlank(message = "City is mandatory") String city,
        @NotBlank(message = "Address is mandatory") String address) {
}
