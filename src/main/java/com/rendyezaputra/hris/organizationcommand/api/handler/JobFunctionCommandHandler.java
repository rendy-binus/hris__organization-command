package com.rendyezaputra.hris.organizationcommand.api.handler;

import com.rendyezaputra.hris.hriswebresources.cqrs.exception.AggregateNotFoundException;
import com.rendyezaputra.hris.hriswebresources.cqrs.exception.ConcurrencyException;
import com.rendyezaputra.hris.organizationcommand.api.command.jobfunction.*;

public interface JobFunctionCommandHandler {
    void handle(CreateJobFunctionCommand command) throws AggregateNotFoundException, ConcurrencyException, IllegalStateException;
    void handle(UpdateJobFunctionCommand command) throws AggregateNotFoundException, ConcurrencyException, IllegalStateException;
    void handle(ActivateJobFunctionCommand command) throws AggregateNotFoundException, ConcurrencyException, IllegalStateException;
    void handle(DeactivateJobFunctionCommand command) throws AggregateNotFoundException, ConcurrencyException, IllegalStateException;
    void handle(DeleteJobFunctionCommand command) throws AggregateNotFoundException, ConcurrencyException, IllegalStateException;
}
