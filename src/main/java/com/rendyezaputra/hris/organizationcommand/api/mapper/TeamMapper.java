package com.rendyezaputra.hris.organizationcommand.api.mapper;

import com.rendyezaputra.hris.organizationcommand.api.command.team.CreateTeamCommand;
import com.rendyezaputra.hris.organizationcommand.api.command.team.UpdateTeamCommand;
import com.rendyezaputra.hris.organizationcommand.api.dto.request.team.CreateTeamRequest;
import com.rendyezaputra.hris.organizationcommand.api.dto.request.team.UpdateTeamRequest;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TeamMapper {

    public CreateTeamCommand toCommand(CreateTeamRequest request) {
        return CreateTeamCommand.builder()
                .id(UUID.randomUUID())
                .name(request.getName())
                .color(request.getColor())
                .build();
    }

    public UpdateTeamCommand toCommand(UpdateTeamRequest request) {
        return UpdateTeamCommand.builder()
                .id(request.getId())
                .name(request.getName())
                .color(request.getColor())
                .build();
    }
}
