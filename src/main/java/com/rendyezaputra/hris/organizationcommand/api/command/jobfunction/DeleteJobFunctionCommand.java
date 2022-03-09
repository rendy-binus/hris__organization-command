package com.rendyezaputra.hris.organizationcommand.api.command.jobfunction;

import com.rendyezaputra.hris.hriswebresources.cqrs.command.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DeleteJobFunctionCommand extends BaseCommand {
    private final boolean active = false;
    private final boolean deleted = true;
}
