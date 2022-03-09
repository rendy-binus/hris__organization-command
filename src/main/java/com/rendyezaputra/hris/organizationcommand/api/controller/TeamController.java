package com.rendyezaputra.hris.organizationcommand.api.controller;

import com.rendyezaputra.hris.hriswebresources.cqrs.command.CommandDispatcher;
import com.rendyezaputra.hris.hriswebresources.dto.BaseResponse;
import com.rendyezaputra.hris.organizationcommand.api.command.team.*;
import com.rendyezaputra.hris.organizationcommand.api.dto.request.team.CreateTeamRequest;
import com.rendyezaputra.hris.organizationcommand.api.dto.request.team.UpdateTeamRequest;
import com.rendyezaputra.hris.organizationcommand.api.dto.response.TeamResponse;
import com.rendyezaputra.hris.organizationcommand.api.mapper.TeamMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/team")
public class TeamController {
    private final CommandDispatcher commandDispatcher;
    private final TeamMapper mapper;

    @PostMapping
    public ResponseEntity<BaseResponse> createTeam(@RequestBody @Valid CreateTeamRequest request) {
        CreateTeamCommand command = mapper.toCommand(request);
        command.setLoginId("mockuser");

        try {
            commandDispatcher.send(command);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(TeamResponse.builder()
                            .id(command.getId())
                            .build());
        } catch (IllegalStateException e) {
            log.error(e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(TeamResponse.builder()
                            .message(e.getLocalizedMessage())
                            .build());
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(TeamResponse.builder()
                            .message(e.getLocalizedMessage())
                            .build());
        }
    }

    @PutMapping
    public ResponseEntity<BaseResponse> updateTeam(@RequestBody @Valid UpdateTeamRequest request) {
        UpdateTeamCommand command = mapper.toCommand(request);
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
    public ResponseEntity<BaseResponse> activateTeam(@PathVariable UUID id) {
        ActivateTeamCommand command = new ActivateTeamCommand();
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
    public ResponseEntity<BaseResponse> deactivateTeam(@PathVariable UUID id) {
        DeactivateTeamCommand command = new DeactivateTeamCommand();
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

    @DeleteMapping("{id}")
    public ResponseEntity<BaseResponse> deleteTeam(@PathVariable UUID id) {
        DeleteTeamCommand command = new DeleteTeamCommand();
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
