package com.rendyezaputra.hris.organizationcommand.domain.aggregate;

import com.rendyezaputra.hris.hriswebresources.cqrs.domain.AggregateRoot;
import com.rendyezaputra.hris.hriswebresources.event.team.*;
import com.rendyezaputra.hris.organizationcommand.api.command.team.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
public class TeamAggregate extends AggregateRoot {
    private boolean active;
    private boolean deleted;

    public TeamAggregate(CreateTeamCommand command) {
        LocalDateTime currentTime = LocalDateTime.now();

        raiseEvent(TeamCreatedEvent.builder()
                .id(command.getId())
                .name(command.getName())
                .color(command.getColor())
                .createdBy(command.getLoginId())
                .createdDate(currentTime)
                .updatedBy(command.getLoginId())
                .updatedDate(currentTime)
                .active(true)
                .deleted(false)
                .build()
        );
    }

    public void updateTeam(UpdateTeamCommand command) throws IllegalStateException {
        if (!this.active && !this.deleted) {
            throw new IllegalStateException("Can't update inactive record");
        }
        if (this.deleted) {
            throw new IllegalStateException("Can't update deleted record");
        }

        raiseEvent(TeamUpdatedEvent.builder()
                .id(command.getId())
                .name(command.getName())
                .color(command.getColor())
                .updatedBy(command.getLoginId())
                .updatedDate(LocalDateTime.now())
                .build()
        );
    }

    public void activateTeam(ActivateTeamCommand command) throws IllegalStateException {
        if (this.active && !this.deleted) {
            throw new IllegalStateException("Record already active!");
        }
        if (this.deleted) {
            throw new IllegalStateException("Can't activate deleted record");
        }

        raiseEvent(TeamActivatedEvent.builder()
                .id(command.getId())
                .updatedBy(command.getLoginId())
                .updatedDate(LocalDateTime.now())
                .active(true)
                .build()
        );
    }

    public void deactivateTeam(DeactivateTeamCommand command) throws IllegalStateException {
        if (!this.active && !this.deleted) {
            throw new IllegalStateException("Record already inactive!");
        }
        if (this.deleted) {
            throw new IllegalStateException("Can't deactivate deleted record");
        }

        raiseEvent(TeamDeactivatedEvent.builder()
                .id(command.getId())
                .updatedBy(command.getLoginId())
                .updatedDate(LocalDateTime.now())
                .active(false)
                .build()
        );
    }

    public void deleteTeam(DeleteTeamCommand command) throws IllegalStateException {
        if (this.active) {
            throw new IllegalStateException("Record must be inactive to be deleted!");
        }
        if (this.deleted) {
            throw new IllegalStateException("Record has been deleted already!");
        }

        raiseEvent(TeamDeletedEvent.builder()
                .id(command.getId())
                .updatedBy(command.getLoginId())
                .updatedDate(LocalDateTime.now())
                .active(false)
                .deleted(true)
                .build()
        );
    }

    public void apply(TeamCreatedEvent event) {
        this.id = event.getId();
        this.active = event.isActive();
        this.deleted = event.isDeleted();
    }
    public void apply(TeamUpdatedEvent event) {
        this.id = event.getId();
        this.active = true;
        this.deleted = false;
    }

    public void apply(TeamActivatedEvent event) {
        this.id = event.getId();
        this.active = true;
        this.deleted = false;
    }

    public void apply(TeamDeactivatedEvent event) {
        this.id = event.getId();
        this.active = false;
        this.deleted = false;
    }

    public void apply(TeamDeletedEvent event) {
        this.id = event.getId();
        this.active = false;
        this.deleted = true;
    }

}
