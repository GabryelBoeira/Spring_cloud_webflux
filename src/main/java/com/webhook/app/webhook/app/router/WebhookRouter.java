package com.webhook.app.webhook.app.router;

import com.webhook.app.webhook.app.handler.WebhookHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class WebhookRouter {

    @Bean
    public RouterFunction<ServerResponse> route(WebhookHandler handler) {
        return RouterFunctions.route()
                .POST("/api/v1/webhook/receive", accept(MediaType.APPLICATION_JSON), handler::receiveWebhook)
                .GET("/api/v1/webhook/{id}", accept(MediaType.APPLICATION_JSON), handler::getWebhookEventById)
                .build();
    }

}