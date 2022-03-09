package com.rendyezaputra.hris.organizationcommand.infrastructure.dispatcher;

import com.rendyezaputra.hris.hriswebresources.cqrs.command.BaseCommand;
import com.rendyezaputra.hris.hriswebresources.cqrs.command.CommandDispatcher;
import com.rendyezaputra.hris.hriswebresources.cqrs.exception.AggregateNotFoundException;
import com.rendyezaputra.hris.hriswebresources.cqrs.exception.ConcurrencyException;
import com.rendyezaputra.hris.hriswebresources.cqrs.handler.CommandHandlerMethod;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class OrganizationCommandDispatcher implements CommandDispatcher {
    private final Map<Class<? extends BaseCommand>, List<CommandHandlerMethod>> routes = new HashMap<>();

    @Override
    public <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler) {
        List<CommandHandlerMethod> handlers = routes.computeIfAbsent(type, c -> new LinkedList<>());
        handlers.add(handler);
    }

    @Override
    public void send(BaseCommand command) throws AggregateNotFoundException, ConcurrencyException, IllegalStateException {
        List<CommandHandlerMethod> handlers = routes.get(command.getClass());

        if (handlers == null || handlers.size() < 1) {
            throw new RuntimeException("No command handler is registered");
        }

        if (handlers.size() > 1) {
            throw new RuntimeException("Cannot send command to more than one handler");
        }

        handlers.get(0).handle(command);
    }
}
