package com.rendyezaputra.hris.organizationcommand.infrastructure.handler;

import com.rendyezaputra.hris.hriswebresources.cqrs.document.EventStore;
import com.rendyezaputra.hris.hriswebresources.cqrs.domain.AggregateRoot;
import com.rendyezaputra.hris.hriswebresources.cqrs.event.BaseEvent;
import com.rendyezaputra.hris.hriswebresources.cqrs.exception.AggregateNotFoundException;
import com.rendyezaputra.hris.hriswebresources.cqrs.exception.ConcurrencyException;
import com.rendyezaputra.hris.hriswebresources.cqrs.handler.EventSourcingHandler;
import com.rendyezaputra.hris.organizationcommand.domain.aggregate.TeamAggregate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TeamEventSourcingHandler implements EventSourcingHandler<TeamAggregate> {
    @Qualifier("TeamEventStore")
    private final EventStore eventStore;

    @Override
    public void save(AggregateRoot aggregate) throws ConcurrencyException {
        eventStore.saveEvents(aggregate.getId(), aggregate.getUncommittedChanges(), aggregate.getVersion());
        aggregate.markChangesAsCommitted();
    }

    @Override
    public TeamAggregate getById(UUID id) throws AggregateNotFoundException {
        TeamAggregate aggregate = new TeamAggregate();

        List<BaseEvent> events = eventStore.getEvents(id);
        if (events != null && !events.isEmpty()) {
            aggregate.replayEvents(events);
            int latestVersion = events.stream()
                    .map(BaseEvent::getVersion).max(Comparator.naturalOrder())
                    .orElse(0);

            aggregate.setVersion(latestVersion);
        }

        return aggregate;
    }
}
