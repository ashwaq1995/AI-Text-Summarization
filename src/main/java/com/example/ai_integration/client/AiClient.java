package com.example.ai_integration.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

import java.util.Map;

import com.example.ai_integration.dto.SummarizeRequest;

@Component
public class AiClient {
    private final WebClient client;
    private final String apiKey;
    private final String model;

    public AiClient(
            WebClient aiWebClient,
            @Value("${ai.api-key}") String apiKey,
            @Value("${ai.model}") String model
    ) {
        this.client = aiWebClient;
        this.apiKey = apiKey;
        this.model = model;
    }

    public Mono<String> summarize(SummarizeRequest req) {
        Map<String, Object> payload = Map.of(
                "model", model,
                "task", "summarize",
                "input", req.text(),
                "max_tokens", req.maxTokens() == null ? 120 : req.maxTokens()
        );

        return client.post()
                .uri("/generate")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + apiKey)
                .bodyValue(payload)
                .retrieve()
                .bodyToMono(Map.class)
                .map(resp -> {
                    // Extract the text based on your provider’s response shape
                    Object summary = resp.getOrDefault("summary", resp.get("text"));
                    return summary == null ? "" : summary.toString();
                });
    }
}
