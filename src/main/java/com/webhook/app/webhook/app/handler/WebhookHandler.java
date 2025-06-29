package com.webhook.app.webhook.app.handler;

import com.webhook.app.webhook.app.dto.WebhookPayloadDTO;
import com.webhook.app.webhook.app.service.WebhookService;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class WebhookHandler {

    private final WebhookService webhookService;
    private final Validator validator;

    public Mono<ServerResponse> receiveWebhook(ServerRequest request) {
        return request.bodyToMono(WebhookPayloadDTO.class)
                .flatMap(dto -> {
                    var violations = validator.validate(dto);
                    if (!violations.isEmpty()) {
                        String errorMessage = violations.stream()
                                .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                                .collect(Collectors.joining(", "));
                        return ServerResponse.badRequest().bodyValue("Erro de validação: " + errorMessage);
                    }
                    return webhookService.saveWebhookEvent(dto)
                            .flatMap(savedEvent -> ServerResponse.status(HttpStatus.CREATED)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .bodyValue(savedEvent))
                            .onErrorResume(e -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                    .bodyValue("Erro ao processar webhook: " + e.getMessage()));
                })
                .switchIfEmpty(ServerResponse.badRequest().bodyValue("Corpo da requisição vazio ou inválido."));
    }

    public Mono<ServerResponse> getWebhookEventById(ServerRequest request) {
        String idString = request.pathVariable("id");
        try {
            UUID id = UUID.fromString(idString);
            return webhookService.findById(id)
                    .flatMap(event -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(event))
                    .switchIfEmpty(ServerResponse.notFound().build());
        } catch (IllegalArgumentException e) {
            return ServerResponse.badRequest().bodyValue("ID inválido: " + idString);
        }
    }

}