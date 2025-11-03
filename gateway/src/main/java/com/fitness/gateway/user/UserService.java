package com.fitness.gateway.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    // we have used @RequiredArgsConstructor so we can mention variable as final to inject dependency injection using IOC
    private final WebClient userServiceWebClient;   // created a bean in config class to connect to user-service for validating user by userId

    // validate whether the user is valid or not
    public Mono<Boolean> validateUser(String userId){

        log.info("Calling User Validation API for userId: {}", userId);
            return userServiceWebClient.get()       // get() is a get() request to user-service
                    .uri("/api/users/{userId}/validate",userId)
                    .retrieve() // this method is going to make the api call to user-service
                    .bodyToMono(Boolean.class)
                    .onErrorResume(WebClientResponseException.class, e -> {
                        if(e.getStatusCode() == HttpStatus.NOT_FOUND)
                            return Mono.error(new RuntimeException("User Not Found: " + userId));
                        else if(e.getStatusCode() == HttpStatus.BAD_REQUEST)
                            return Mono.error(new RuntimeException("Invalid Request: " + userId));
                        return Mono.error(new RuntimeException("Unexpected Erro: " + e.getMessage()));

                    });

    }

    // register User in Database
    public Mono<UserResponse> registerUser(RegisterRequest request) {

        log.info("Calling User Registration API for userId: {}", request.getEmail());
        return userServiceWebClient.post()       // get() is a get() request to user-service
                .uri("/api/users/register")
                .bodyValue(request)
                .retrieve() // this method is going to make the api call to user-service
                .bodyToMono(UserResponse.class)
                .onErrorResume(WebClientResponseException.class, e -> {
                    if(e.getStatusCode() == HttpStatus.BAD_REQUEST)
                        return Mono.error(new RuntimeException("Bad Request: " + e.getMessage()));
                    else if(e.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR)
                        return Mono.error(new RuntimeException("Internal Server Error: " + e.getMessage()));
                    return Mono.error(new RuntimeException("Unexpected Erro: " + e.getMessage()));

                });

    }



}
