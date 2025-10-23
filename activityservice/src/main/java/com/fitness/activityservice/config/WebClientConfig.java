package com.fitness.activityservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean
    @LoadBalanced   //   by this annotation we will be able to see other microservices using their application/service name
    public WebClient.Builder webClientBuilder(){
        return WebClient.builder();
    }


    // bean to call user-service from activity-service using web-client
    @Bean
    public WebClient userServiceWebClient(WebClient.Builder webClientBuilder){
        return webClientBuilder
                .baseUrl("http://USER-SERVICE")
                .build();
    }

}
