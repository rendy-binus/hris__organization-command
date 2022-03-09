package com.rendyezaputra.hris.organizationcommand.api.command.jobfunction;

import com.rendyezaputra.hris.hriswebresources.cqrs.command.BaseCommand;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreateJobFunctionCommand extends BaseCommand {
    private String name;
    private short level;
    private String color;

    @Builder
    public CreateJobFunctionCommand(UUID id, String loginId, String name, short level, String color) {
        super(id, loginId);
        this.name = name;
        this.level = level;
        this.color = color;
    }
}
