package com.rendyezaputra.hris.organizationcommand;

import com.rendyezaputra.hris.hriswebresources.cqrs.command.CommandDispatcher;
import com.rendyezaputra.hris.organizationcommand.api.command.jobfunction.*;
import com.rendyezaputra.hris.organizationcommand.api.command.team.*;
import com.rendyezaputra.hris.organizationcommand.api.handler.JobFunctionCommandHandler;
import com.rendyezaputra.hris.organizationcommand.api.handler.TeamCommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableDiscoveryClient
@EnableMongoRepositories
@RequiredArgsConstructor
public class OrganizationCommandApplication {
    private final CommandDispatcher commandDispatcher;
    private final JobFunctionCommandHandler jobFunctionCommandHandler;
    private final TeamCommandHandler teamCommandHandler;

    public static void main(String[] args) {
        SpringApplication.run(OrganizationCommandApplication.class, args);
    }

    @PostConstruct
    public void registerHandlers() {
        commandDispatcher.registerHandler(CreateJobFunctionCommand.class,
                jobFunctionCommandHandler::handle);
        commandDispatcher.registerHandler(UpdateJobFunctionCommand.class,
                jobFunctionCommandHandler::handle);
        commandDispatcher.registerHandler(ActivateJobFunctionCommand.class,
                jobFunctionCommandHandler::handle);
        commandDispatcher.registerHandler(DeactivateJobFunctionCommand.class,
                jobFunctionCommandHandler::handle);
        commandDispatcher.registerHandler(DeleteJobFunctionCommand.class,
                jobFunctionCommandHandler::handle);

        commandDispatcher.registerHandler(CreateTeamCommand.class,
                teamCommandHandler::handle);
        commandDispatcher.registerHandler(UpdateTeamCommand.class,
                teamCommandHandler::handle);
        commandDispatcher.registerHandler(ActivateTeamCommand.class,
                teamCommandHandler::handle);
        commandDispatcher.registerHandler(DeactivateTeamCommand.class,
                teamCommandHandler::handle);
        commandDispatcher.registerHandler(DeleteTeamCommand.class,
                teamCommandHandler::handle);
    }

}
