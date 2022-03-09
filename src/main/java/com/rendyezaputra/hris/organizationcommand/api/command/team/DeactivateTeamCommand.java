package com.rendyezaputra.hris.organizationcommand.api.command.team;

import com.rendyezaputra.hris.hriswebresources.cqrs.command.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DeactivateTeamCommand extends BaseCommand {
    private final boolean active = false;
}
