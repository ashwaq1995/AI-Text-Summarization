package com.example.ai_integration.service;

import org.springframework.stereotype.Service;

import com.example.ai_integration.client.AiClient;
import com.example.ai_integration.dto.SummarizeRequest;
import com.example.ai_integration.dto.SummarizeResponse;

import reactor.core.publisher.Mono;

@Service
public class AiService {
    private final AiClient aiClient;

    public AiService(AiClient aiClient) {
        this.aiClient = aiClient;
    }

    public Mono<SummarizeResponse> summarize(SummarizeRequest req) {
        return aiClient.summarize(req)
                .map(SummarizeResponse::new);
    }
}
