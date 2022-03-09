package com.rendyezaputra.hris.organizationcommand.api.handler.implementation;

import com.rendyezaputra.hris.hriswebresources.cqrs.exception.AggregateNotFoundException;
import com.rendyezaputra.hris.hriswebresources.cqrs.exception.ConcurrencyException;
import com.rendyezaputra.hris.organizationcommand.api.command.jobfunction.*;
import com.rendyezaputra.hris.organizationcommand.api.handler.JobFunctionCommandHandler;
import com.rendyezaputra.hris.organizationcommand.domain.aggregate.JobFunctionAggregate;
import com.rendyezaputra.hris.organizationcommand.infrastructure.handler.JobFunctionEventSourcingHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JobFunctionCommandHandlerImpl implements JobFunctionCommandHandler {
    private final JobFunctionEventSourcingHandler eventSourcingHandler;

    @Override
    public void handle(CreateJobFunctionCommand command) throws AggregateNotFoundException, ConcurrencyException, IllegalStateException {
        JobFunctionAggregate aggregate = new JobFunctionAggregate(command);
        eventSourcingHandler.save(aggregate);
    }

    @Override
    public void handle(UpdateJobFunctionCommand command) throws AggregateNotFoundException, ConcurrencyException, IllegalStateException {
        JobFunctionAggregate aggregate = eventSourcingHandler.getById(command.getId());
        aggregate.updateJobFunction(command);
        eventSourcingHandler.save(aggregate);
    }

    @Override
    public void handle(ActivateJobFunctionCommand command) throws AggregateNotFoundException, ConcurrencyException, IllegalStateException {
        JobFunctionAggregate aggregate = eventSourcingHandler.getById(command.getId());
        aggregate.activateJobFunction(command);
        eventSourcingHandler.save(aggregate);
    }

    @Override
    public void handle(DeactivateJobFunctionCommand command) throws AggregateNotFoundException, ConcurrencyException, IllegalStateException {
        JobFunctionAggregate aggregate = eventSourcingHandler.getById(command.getId());
        aggregate.deactivateJobFunction(command);
        eventSourcingHandler.save(aggregate);
    }

    @Override
    public void handle(DeleteJobFunctionCommand command) throws AggregateNotFoundException, ConcurrencyException, IllegalStateException {
        JobFunctionAggregate aggregate = eventSourcingHandler.getById(command.getId());
        aggregate.deleteJobFunction(command);
        eventSourcingHandler.save(aggregate);
    }
}
