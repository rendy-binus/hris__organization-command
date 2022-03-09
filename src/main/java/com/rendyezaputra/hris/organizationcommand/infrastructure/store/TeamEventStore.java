package com.rendyezaputra.hris.organizationcommand.infrastructure.store;

import com.rendyezaputra.hris.hriswebresources.cqrs.document.EventStore;
import com.rendyezaputra.hris.hriswebresources.cqrs.event.BaseEvent;
import com.rendyezaputra.hris.hriswebresources.cqrs.exception.AggregateNotFoundException;
import com.rendyezaputra.hris.hriswebresources.cqrs.exception.ConcurrencyException;
import com.rendyezaputra.hris.hriswebresources.event.EventProducer;
import com.rendyezaputra.hris.organizationcommand.domain.aggregate.JobFunctionAggregate;
import com.rendyezaputra.hris.organizationcommand.domain.document.TeamEventDocument;
import com.rendyezaputra.hris.organizationcommand.domain.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Qualifier("TeamEventStore")
@Service
public class TeamEventStore implements EventStore {
    private final TeamRepository repository;
    private final EventProducer producer;

    @Override
    public void saveEvents(UUID aggregateId, Iterable<BaseEvent> events, int expectedVersion) throws ConcurrencyException {
        List<TeamEventDocument> eventDocuments = repository.findByAggregateIdentifier(aggregateId.toString());

        if (expectedVersion != -1 && eventDocuments.get(eventDocuments.size() - 1).getVersion() != expectedVersion) {
            throw new ConcurrencyException();
        }

        int version = expectedVersion;
        for (BaseEvent event : events) {
            version++;
            event.setVersion(version);

            TeamEventDocument eventDocument = TeamEventDocument.builder()
                    .timestamp(LocalDateTime.now())
                    .aggregateIdentifier(aggregateId.toString())
                    .aggregateType(JobFunctionAggregate.class.getTypeName())
                    .version(version)
                    .eventType(event.getClass().getTypeName())
                    .eventData(event)
                    .build();

            TeamEventDocument savedEventDocument = repository.save(eventDocument);

            if (!savedEventDocument.getId().isEmpty()) {
                producer.produce(event.getClass().getSimpleName(), savedEventDocument.getId(), event);
            }
        }
    }

    @Override
    public List<BaseEvent> getEvents(UUID aggregateId) throws AggregateNotFoundException {
        List<TeamEventDocument> eventDocuments = repository.findByAggregateIdentifier(aggregateId.toString());

        if (eventDocuments == null || eventDocuments.isEmpty()) {
            throw new AggregateNotFoundException("Team ID is incorrect");
        }

        return eventDocuments.stream()
                .map(TeamEventDocument::getEventData)
                .collect(Collectors.toList());
    }
}
