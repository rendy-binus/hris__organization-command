package com.rendyezaputra.hris.organizationcommand.api.command.jobfunction;

import com.rendyezaputra.hris.hriswebresources.cqrs.command.BaseCommand;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateJobFunctionCommand extends BaseCommand {
    private String name;
    private short level;
    private String color;
    private String updatedBy;
    private LocalDateTime updatedDate;

    @Builder
    public UpdateJobFunctionCommand(UUID id, String loginId, String name, short level, String color, String updatedBy, LocalDateTime updatedDate) {
        super(id, loginId);
        this.name = name;
        this.level = level;
        this.color = color;
        this.updatedBy = updatedBy;
        this.updatedDate = updatedDate;
    }
}
