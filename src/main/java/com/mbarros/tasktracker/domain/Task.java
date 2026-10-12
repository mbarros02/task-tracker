package com.mbarros.tasktracker.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Task {
    private final int id;
    private String description;
    private TaskStatus status;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Task(int id, String description, TaskStatus status,
                 LocalDateTime createdAt, LocalDateTime updatedAt) {
        validateId(id);
        this.id = id;
        validateDescription(description);
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Task create(int id, String description) {
        LocalDateTime now = LocalDateTime.now();
        return new Task(id, description, TaskStatus.TODO, now, now);
    }

    public static Task restore(int id, String description, TaskStatus status,
                               LocalDateTime createdAt, LocalDateTime updatedAt) {
        Objects.requireNonNull(status, "Status cannot be null.");
        Objects.requireNonNull(createdAt, "Creation date cannot be null.");
        Objects.requireNonNull(updatedAt, "Update date cannot be null.");
        return new Task(id, description, status, createdAt, updatedAt);
    }

    public String getDescription() {
        return description;
    }

    private void validateDescription(String description) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("The description can not be blank or empty.");
        }
    }

    private void validateId(int id) {
        if (id < 1) {
            throw new IllegalArgumentException("The task ID cannot be less than 1.");
        }
    }

    public void updateDescription(String newDescription) {
        validateDescription(newDescription);
        this.description = newDescription;
        this.updatedAt = LocalDateTime.now();
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void markInProgress() {
        this.status = TaskStatus.IN_PROGRESS;
        this.updatedAt = LocalDateTime.now();
    }

    public void markDone() {
        this.status = TaskStatus.DONE;
        this.updatedAt = LocalDateTime.now();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "Task: " + description + "\n" +
                "Id: " + id + "\n" +
                "Status: " + status + "\n" +
                "CreatedAt: " + createdAt + "\n" +
                "UpdatedAt: " + updatedAt;
    }
}
