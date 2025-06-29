package com.webhook.app.webhook.app.repositoy;

import com.webhook.app.webhook.app.model.WebhookEvent;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WebhookEventRepository extends ReactiveCrudRepository<WebhookEvent, UUID> {
}
