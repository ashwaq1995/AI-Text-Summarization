package com.example.ai_integration.controller;

import jakarta.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.example.ai_integration.dto.SummarizeRequest;
import com.example.ai_integration.dto.SummarizeResponse;
import com.example.ai_integration.service.AiService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    private final AiService aiService;

    public AiController(AiService aiService) {
        this.aiService = aiService;
    }

    @PostMapping(value = "/summarize", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<SummarizeResponse> summarize(@Valid @RequestBody SummarizeRequest request) {
        return aiService.summarize(request);
    }
}
