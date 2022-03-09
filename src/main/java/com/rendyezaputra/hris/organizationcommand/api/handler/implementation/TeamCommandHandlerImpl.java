package com.rendyezaputra.hris.organizationcommand.api.handler.implementation;

import com.rendyezaputra.hris.hriswebresources.cqrs.exception.AggregateNotFoundException;
import com.rendyezaputra.hris.hriswebresources.cqrs.exception.ConcurrencyException;
import com.rendyezaputra.hris.organizationcommand.api.command.team.*;
import com.rendyezaputra.hris.organizationcommand.api.handler.TeamCommandHandler;
import com.rendyezaputra.hris.organizationcommand.domain.aggregate.TeamAggregate;
import com.rendyezaputra.hris.organizationcommand.infrastructure.handler.TeamEventSourcingHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TeamCommandHandlerImpl implements TeamCommandHandler {
    private final TeamEventSourcingHandler eventSourcingHandler;

    @Override
    public void handle(CreateTeamCommand command) throws AggregateNotFoundException, ConcurrencyException, IllegalStateException {
        TeamAggregate aggregate = new TeamAggregate(command);
        eventSourcingHandler.save(aggregate);
    }

    @Override
    public void handle(UpdateTeamCommand command) throws AggregateNotFoundException, ConcurrencyException, IllegalStateException {
        TeamAggregate aggregate = eventSourcingHandler.getById(command.getId());
        aggregate.updateTeam(command);
        eventSourcingHandler.save(aggregate);
    }

    @Override
    public void handle(ActivateTeamCommand command) throws AggregateNotFoundException, ConcurrencyException, IllegalStateException {
        TeamAggregate aggregate = eventSourcingHandler.getById(command.getId());
        aggregate.activateTeam(command);
        eventSourcingHandler.save(aggregate);
    }

    @Override
    public void handle(DeactivateTeamCommand command) throws AggregateNotFoundException, ConcurrencyException, IllegalStateException {
        TeamAggregate aggregate = eventSourcingHandler.getById(command.getId());
        aggregate.deactivateTeam(command);
        eventSourcingHandler.save(aggregate);
    }

    @Override
    public void handle(DeleteTeamCommand command) throws AggregateNotFoundException, ConcurrencyException, IllegalStateException {
        TeamAggregate aggregate = eventSourcingHandler.getById(command.getId());
        aggregate.deleteTeam(command);
        eventSourcingHandler.save(aggregate);
    }
}
