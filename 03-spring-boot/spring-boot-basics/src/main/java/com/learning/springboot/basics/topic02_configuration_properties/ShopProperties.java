package com.learning.springboot.basics.topic02_configuration_properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

/*
 * Topic    : Type-safe configuration with @ConfigurationProperties
 * Key idea : Put a whole GROUP of settings (everything under app.shop in application.yml)
 *            into one typed, read-only, validated object - instead of many separate @Value strings.
 *            Like a filled-in form with all the shop's details, instead of loose chits of paper.
 *              - relaxed binding: max-items, maxItems and MAX_ITEMS all fill maxItems
 *              - @Validated: a wrong value stops the app AT STARTUP with a clear message,
 *                not hours later in production
 *              - registered by @ConfigurationPropertiesScan on BasicsApplication
 * Try this : Set discount-percent: 95 in application.yml and read the startup error.
 */
@Validated
@ConfigurationProperties(prefix = "app.shop")
public record ShopProperties(
        @NotBlank String currency,
        @Min(1) int maxItems,
        @Min(0) @Max(90) int discountPercent,
        @Valid Contact contact) {

    public record Contact(@NotBlank @Email String email, String phone) {
    }
}
