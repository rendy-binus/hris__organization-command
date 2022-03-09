package com.rendyezaputra.hris.organizationcommand.api.command.team;

import com.rendyezaputra.hris.hriswebresources.cqrs.command.BaseCommand;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateTeamCommand extends BaseCommand {
    private String name;
    private String color;
    private String updatedBy;
    private LocalDateTime updatedDate;

    @Builder
    public UpdateTeamCommand(UUID id, String loginId, String name, String color, String updatedBy, LocalDateTime updatedDate) {
        super(id, loginId);
        this.name = name;
        this.color = color;
        this.updatedBy = updatedBy;
        this.updatedDate = updatedDate;
    }
}
