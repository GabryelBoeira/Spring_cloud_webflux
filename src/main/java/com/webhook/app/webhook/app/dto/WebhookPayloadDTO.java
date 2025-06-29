package com.webhook.app.webhook.app.dto;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebhookPayloadDTO {

    @NotBlank(message = "O tipo de evento não pode ser vazio")
    private String eventType;

    @NotNull(message = "O payload (data) não pode ser nulo")
    private JsonNode data; // Para um payload JSON flexível
}