package com.rendyezaputra.hris.organizationcommand.api.command.team;

import com.rendyezaputra.hris.hriswebresources.cqrs.command.BaseCommand;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreateTeamCommand extends BaseCommand {
    private String name;
    private String color;

    @Builder
    public CreateTeamCommand(UUID id, String loginId, String name, String color) {
        super(id, loginId);
        this.name = name;
        this.color = color;
    }
}
