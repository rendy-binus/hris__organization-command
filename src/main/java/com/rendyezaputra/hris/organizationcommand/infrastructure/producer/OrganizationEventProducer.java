package com.rendyezaputra.hris.organizationcommand.infrastructure.producer;

import com.rendyezaputra.hris.hriswebresources.cqrs.event.BaseEvent;
import com.rendyezaputra.hris.hriswebresources.event.EventProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrganizationEventProducer implements EventProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void produce(String topic, String key, BaseEvent event) {
        log.debug("Producing event to topic: {} with key: {}\n" +
                "event details: {}", topic, key, event);
        this.kafkaTemplate.send(topic, key, event).addCallback(
                result -> {
                    final RecordMetadata m;
                    if (result != null) {
                        m = result.getRecordMetadata();
                        log.info("Produced record to topic {} partition {} @ offset {}",
                                m.topic(),
                                m.partition(),
                                m.offset());
                    }
                },
                exception -> log.error("Failed to produce to Kafka: {}", exception.getLocalizedMessage())
        );
    }
}
