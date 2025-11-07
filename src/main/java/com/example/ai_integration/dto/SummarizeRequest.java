package com.example.ai_integration.dto;

import jakarta.validation.constraints.NotBlank;

public record SummarizeRequest(
        @NotBlank String text,
        Integer maxTokens // optional
) {}
