package com.webhook.app.webhook.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("webhook_events")
public class WebhookEvent {

    @Id
    private UUID id;
    @Column("event_type")
    private String eventType;
    @Column("payload")
    private String payload; // JSON como String
    @Column("received_at")
    private LocalDateTime receivedAt;
    private boolean processed;
    private String processingNotes;

    // Construtor para o recebimento inicial
    public WebhookEvent(String eventType, String payload) {
        this.eventType = eventType;
        this.payload = payload;
        this.receivedAt = LocalDateTime.now();
        this.processed = false;
    }
}