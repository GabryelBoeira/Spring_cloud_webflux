package com.webhook.app.webhook.app.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webhook.app.webhook.app.dto.WebhookPayloadDTO;
import com.webhook.app.webhook.app.model.WebhookEvent;
import com.webhook.app.webhook.app.repositoy.WebhookEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class WebhookService {
    private final WebhookEventRepository webhookEventRepository;
    private final ObjectMapper objectMapper;

    public Mono<WebhookEvent> saveWebhookEvent(WebhookPayloadDTO dto) {
        return Mono.defer(() -> {
            try {
                String payloadJson = objectMapper.writeValueAsString(dto.getData());
                WebhookEvent event = new WebhookEvent(dto.getEventType(), payloadJson);
                return webhookEventRepository.save(event).doOnSuccess(savedEvent -> System.out.println("Webhook Event salvo: " + savedEvent.getId())).doOnError(e -> System.err.println("Erro ao salvar webhook event: " + e.getMessage()));
            } catch (JsonProcessingException e) {
                return Mono.error(new RuntimeException("Erro ao serializar payload JSON: " + e.getMessage(), e));
            }
        });
    }

    public Mono<WebhookEvent> findById(java.util.UUID id) {
        return webhookEventRepository.findById(id);
    }
}