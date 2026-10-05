package com.udemy.microservices.currency_conversion_service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

import feign.FeignException;

/*
 * What to answer when the service we depend on fails:
 *   it says the currency pair doesn't exist (404) -> pass on the 404: the CLIENT asked for something wrong
 *   it is down or cannot be reached               -> 503 Service Unavailable: the service WE need has failed
 * Without this, both cases come out as a confusing 500 with a stack-trace message.
 */
@RestControllerAdvice
public class ConversionExceptionHandler {

    @ExceptionHandler({FeignException.NotFound.class, HttpClientErrorException.NotFound.class})
    public ProblemDetail pairNotFound(Exception e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, "currency-exchange has no rate for that currency pair");
    }

    @ExceptionHandler({FeignException.class, RestClientException.class, IllegalStateException.class})
    public ProblemDetail exchangeUnavailable(Exception e) {
        // IllegalStateException: the load balancer found NO currency-exchange instance registered in Eureka
        return ProblemDetail.forStatusAndDetail(HttpStatus.SERVICE_UNAVAILABLE,
                "currency-exchange is not available right now (" + e.getClass().getSimpleName() + ")");
    }
}
