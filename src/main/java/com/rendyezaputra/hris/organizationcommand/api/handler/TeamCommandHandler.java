package com.rendyezaputra.hris.organizationcommand.api.handler;

import com.rendyezaputra.hris.hriswebresources.cqrs.exception.AggregateNotFoundException;
import com.rendyezaputra.hris.hriswebresources.cqrs.exception.ConcurrencyException;
import com.rendyezaputra.hris.organizationcommand.api.command.team.*;

public interface TeamCommandHandler {
    void handle(CreateTeamCommand command) throws AggregateNotFoundException, ConcurrencyException, IllegalStateException;
    void handle(UpdateTeamCommand command) throws AggregateNotFoundException, ConcurrencyException, IllegalStateException;
    void handle(ActivateTeamCommand command) throws AggregateNotFoundException, ConcurrencyException, IllegalStateException;
    void handle(DeactivateTeamCommand command) throws AggregateNotFoundException, ConcurrencyException, IllegalStateException;
    void handle(DeleteTeamCommand command) throws AggregateNotFoundException, ConcurrencyException, IllegalStateException;
}
