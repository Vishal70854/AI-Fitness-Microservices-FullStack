package com.fitness.aiservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class GeminiService {
    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    private final WebClient webClient;
    // autoworing webclient from constructor
    public GeminiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    // api will call the gemini api and it contains body similar to gemini api body
    public String getAnswer(String question){
        Map<String, Object> requestBody = Map.of(   // body structure similar to gemini api body containing Map and Object[] of maps
                "contents", new Object[] {
                        Map.of("parts", new Object[] {
                                Map.of("text", question)
                        })
                }
        );

        // response structure after sending request to gemini api
        String response = webClient.post()
                .uri(geminiApiUrl + geminiApiKey)
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)// passing the body to GEMINI API to get the response in String type
                .retrieve()
                .bodyToMono(String.class)// we want reponse in String type
                .block();

        return response;


    }

}
