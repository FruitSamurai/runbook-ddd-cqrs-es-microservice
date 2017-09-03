package io.cqrs.taskmanagement.domain.model.runbook;

import io.cqrs.taskmanagement.domain.model.Aggregate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Task implements Aggregate {
    private static final String OPENED = "OPEN";
    private static final String IN_PROGRESS = "IN_PROGRESS";
    private static final String COMPLETED = "COMPLETED";

    @Id
    private String taskId;
    private String userId;
    private String status;

    // constructor needed for JPA persistence
    public Task() {
    }

    public Task(String taskId, String userId) {
        this.taskId = taskId;
        this.userId = userId;
        this.status = OPENED;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getUserId() {
        return userId;
    }

    public String getStatus() {
        return status;
    }

    public boolean isInProgress() {
        return this.status.equals(IN_PROGRESS);
    }

    public boolean isClosed() {
        return this.status.equals(COMPLETED);
    }

    //
    // Apply
    //

    void apply(TaskMarkedInProgress e) {
        // TODO Which aggregate should be responsible to apply the task status change?
        this.status = IN_PROGRESS;
    }

    public void apply(TaskCompleted e) {
        this.status = COMPLETED;
    }
}
