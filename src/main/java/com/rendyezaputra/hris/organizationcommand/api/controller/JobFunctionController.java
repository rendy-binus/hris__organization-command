package com.rendyezaputra.hris.organizationcommand.api.controller;

import com.rendyezaputra.hris.hriswebresources.cqrs.command.CommandDispatcher;
import com.rendyezaputra.hris.hriswebresources.dto.BaseResponse;
import com.rendyezaputra.hris.organizationcommand.api.command.jobfunction.*;
import com.rendyezaputra.hris.organizationcommand.api.dto.request.jobfunction.CreateJobFunctionRequest;
import com.rendyezaputra.hris.organizationcommand.api.dto.request.jobfunction.UpdateJobFunctionRequest;
import com.rendyezaputra.hris.organizationcommand.api.dto.response.JobFunctionResponse;
import com.rendyezaputra.hris.organizationcommand.api.mapper.JobFunctionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping(path = "/job-function")
public class JobFunctionController {
    private final CommandDispatcher commandDispatcher;
    private final JobFunctionMapper mapper;

    @PostMapping
    public ResponseEntity<BaseResponse> createJobFunction(@RequestBody @Valid CreateJobFunctionRequest request) {
        CreateJobFunctionCommand command = mapper.toCommand(request);
        command.setLoginId("mockuser");

        try {
            commandDispatcher.send(command);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(JobFunctionResponse.builder()
                            .id(command.getId())
                            .build());
        } catch (IllegalStateException e) {
            log.error(e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(JobFunctionResponse.builder()
                            .message(e.getLocalizedMessage())
                            .build());
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(JobFunctionResponse.builder()
                            .message(e.getLocalizedMessage())
                            .build());
        }
    }

    @PutMapping
    public ResponseEntity<BaseResponse> updateJobFunction(@RequestBody @Valid UpdateJobFunctionRequest request) {
        UpdateJobFunctionCommand command = mapper.toCommand(request);
        command.setLoginId("mockuser");

        BaseResponse response = new BaseResponse();

        try {
            commandDispatcher.send(command);

            response.setMessage("command received");
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(response);
        } catch (IllegalStateException e) {
            log.error(e.getLocalizedMessage());
            response.setMessage(e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(response);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            response.setMessage(e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }
    }

    @PatchMapping("/activate/{id}")
    public ResponseEntity<BaseResponse> activateJobFunction(@PathVariable UUID id) {
        ActivateJobFunctionCommand command = new ActivateJobFunctionCommand();
        command.setLoginId("mockuser");
        command.setId(id);

        BaseResponse response = new BaseResponse();

        try {
            commandDispatcher.send(command);

            response.setMessage("command received");
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(response);
        } catch (IllegalStateException e) {
            log.error(e.getLocalizedMessage());
            response.setMessage(e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(response);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            response.setMessage(e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }
    }

    @PatchMapping("/deactivate/{id}")
    public ResponseEntity<BaseResponse> deactivateJobFunction(@PathVariable UUID id) {
        DeactivateJobFunctionCommand command = new DeactivateJobFunctionCommand();
        command.setLoginId("mockuser");
        command.setId(id);

        BaseResponse response = new BaseResponse();

        try {
            commandDispatcher.send(command);

            response.setMessage("command received");
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(response);
        } catch (IllegalStateException e) {
            log.error(e.getLocalizedMessage());
            response.setMessage(e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(response);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            response.setMessage(e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteJobFunction(@PathVariable UUID id) {
        DeleteJobFunctionCommand command = new DeleteJobFunctionCommand();
        command.setLoginId("mockuser");
        command.setId(id);

        BaseResponse response = new BaseResponse();

        try {
            commandDispatcher.send(command);

            response.setMessage("command received");
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(response);
        } catch (IllegalStateException e) {
            log.error(e.getLocalizedMessage());
            response.setMessage(e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(response);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            response.setMessage(e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }
    }

}
