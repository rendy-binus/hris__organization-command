package com.rendyezaputra.hris.organizationcommand.domain.aggregate;

import com.rendyezaputra.hris.hriswebresources.cqrs.domain.AggregateRoot;
import com.rendyezaputra.hris.hriswebresources.event.jobfunction.*;
import com.rendyezaputra.hris.organizationcommand.api.command.jobfunction.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
public class JobFunctionAggregate extends AggregateRoot {
    private boolean active;
    private boolean deleted;

    public JobFunctionAggregate(CreateJobFunctionCommand command) {
        LocalDateTime currentTime = LocalDateTime.now();

        raiseEvent(JobFunctionCreatedEvent.builder()
                .id(command.getId())
                .name(command.getName())
                .level(command.getLevel())
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

    public void updateJobFunction(UpdateJobFunctionCommand command) throws IllegalStateException {
        if (!this.active && !this.deleted) {
            throw new IllegalStateException("Can't update inactive record");
        }
        if (this.deleted) {
            throw new IllegalStateException("Can't update deleted record");
        }

        raiseEvent(JobFunctionUpdatedEvent.builder()
                .id(command.getId())
                .name(command.getName())
                .level(command.getLevel())
                .color(command.getColor())
                .updatedBy(command.getLoginId())
                .updatedDate(LocalDateTime.now())
                .build()
        );
    }

    public void activateJobFunction(ActivateJobFunctionCommand command) throws IllegalStateException {
        if (this.active && !this.deleted) {
            throw new IllegalStateException("Record already active!");
        }
        if (this.deleted) {
            throw new IllegalStateException("Can't activate deleted record");
        }

        raiseEvent(JobFunctionActivatedEvent.builder()
                .id(command.getId())
                .updatedBy(command.getLoginId())
                .updatedDate(LocalDateTime.now())
                .active(true)
                .build()
        );
    }

    public void deactivateJobFunction(DeactivateJobFunctionCommand command) throws IllegalStateException {
        if (!this.active && !this.deleted) {
            throw new IllegalStateException("Record already inactive!");
        }
        if (this.deleted) {
            throw new IllegalStateException("Can't deactivate deleted record");
        }

        raiseEvent(JobFunctionDeactivatedEvent.builder()
                .id(command.getId())
                .updatedBy(command.getLoginId())
                .updatedDate(LocalDateTime.now())
                .active(false)
                .build()
        );
    }

    public void deleteJobFunction(DeleteJobFunctionCommand command) throws IllegalStateException {
        if (this.active) {
            throw new IllegalStateException("Record must be inactive to be deleted!");
        }
        if (this.deleted) {
            throw new IllegalStateException("Record has been deleted already!");
        }

        raiseEvent(JobFunctionDeletedEvent.builder()
                .id(command.getId())
                .updatedBy(command.getLoginId())
                .updatedDate(LocalDateTime.now())
                .active(false)
                .deleted(true)
                .build()
        );
    }

    public void apply(JobFunctionCreatedEvent event) {
        this.id = event.getId();
        this.active = event.isActive();
        this.deleted = event.isDeleted();
    }

    public void apply(JobFunctionUpdatedEvent event) {
        this.id = event.getId();
        this.active = true;
        this.deleted = false;
    }

    public void apply(JobFunctionActivatedEvent event) {
        this.id = event.getId();
        this.active = true;
        this.deleted = false;
    }

    public void apply(JobFunctionDeactivatedEvent event) {
        this.id = event.getId();
        this.active = false;
        this.deleted = false;
    }

    public void apply(JobFunctionDeletedEvent event) {
        this.id = event.getId();
        this.active = false;
        this.deleted = true;
    }
}
