package com.rendyezaputra.hris.organizationcommand.api.mapper;

import com.rendyezaputra.hris.organizationcommand.api.command.jobfunction.CreateJobFunctionCommand;
import com.rendyezaputra.hris.organizationcommand.api.command.jobfunction.UpdateJobFunctionCommand;
import com.rendyezaputra.hris.organizationcommand.api.dto.request.jobfunction.CreateJobFunctionRequest;
import com.rendyezaputra.hris.organizationcommand.api.dto.request.jobfunction.UpdateJobFunctionRequest;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class JobFunctionMapper {

    public CreateJobFunctionCommand toCommand(CreateJobFunctionRequest request) {
        return CreateJobFunctionCommand.builder()
                .id(UUID.randomUUID())
                .name(request.getName())
                .level(request.getLevel())
                .color(request.getColor())
                .build();
    }

    public UpdateJobFunctionCommand toCommand(UpdateJobFunctionRequest request) {
        return UpdateJobFunctionCommand.builder()
                .id(request.getId())
                .name(request.getName())
                .level(request.getLevel())
                .color(request.getColor())
                .build();
    }

}
