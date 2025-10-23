package com.fitness.activityservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserValidationService {
    // we have used @RequiredArgsConstructor so we can mention variable as final to inject dependency injection using IOC
    private final WebClient userServiceWebClient;   // created a bean in config class to connect to user-service for validating user by userId

    public boolean validateUser(String userId){
        log.info("Calling User Validation API for userId: {}", userId);
        try {
            return userServiceWebClient.get()       // get() is a get() request to user-service
                    .uri("/api/users/{userId}/validate",userId)
                    .retrieve() // this method is going to make the api call to user-service
                    .bodyToMono(Boolean.class)
                    .block();
        } catch (WebClientResponseException e) {
            if(e.getStatusCode() == HttpStatus.NOT_FOUND){
                throw new RuntimeException("User Not Found : " + userId);
            }
            else{
                throw new RuntimeException("Invalid Request : " + userId);

            }
        }
    }
}
