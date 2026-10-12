package com.mbarros.tasktracker.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    private static final LocalDateTime CREATED_AT = LocalDateTime.of(2026, 1, 26, 14, 10);
    private static final LocalDateTime OLD_UPDATED_AT = LocalDateTime.of(2026, 2, 15, 15, 20);

    // Build Tests
    @Test
    void create_withBlankDescription_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            Task.create(1, " ");
        });
    }

    @Test
    void create_withNullDescription_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            Task.create(1, null);
        });
    }

    @Test
    void create_startsWithTodoStatus() {
        Task task = Task.create(1, "Test Task");
        assertEquals(TaskStatus.TODO, task.getStatus());
    }

    @Test
    void create_storesIdAndDescription() {
        Task task = Task.create(10, "Test Task");

        assertEquals(10, task.getId());

        assertEquals("Test Task", task.getDescription());
    }

    @Test
    void create_withZeroId_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            Task.create(0, "Test Task");
        });
    }

    @Test
    void create_withNegativeId_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            Task.create(-1, "Test Task");
        });
    }

    @Test
    void create_setsUpdatedAtEqualToCreatedAt() {

    }

    // Restore Tests
    @Test
    void restore_preservesAllFields() {
        Task task = Task.restore(5, "Test Task Restore", TaskStatus.IN_PROGRESS,
                CREATED_AT, OLD_UPDATED_AT);
        assertEquals(5, task.getId());
        assertEquals("Test Task Restore", task.getDescription());
        assertEquals(TaskStatus.IN_PROGRESS, task.getStatus());
        assertEquals(task.getCreatedAt(), CREATED_AT);
        assertEquals(task.getUpdatedAt(), OLD_UPDATED_AT);
    }

    @Test
    void restore_withNullStatus_throwsException() {
        assertThrows(NullPointerException.class, () -> {
            Task.restore(5, "Test", null, CREATED_AT, OLD_UPDATED_AT);
        });
    }

    @Test
    void restore_withNullCreatedAt_throwsException() {
        assertThrows(NullPointerException.class, () -> {
            Task.restore(5, "Test", TaskStatus.TODO, null, OLD_UPDATED_AT);
        });
    }

    @Test
    void restore_withNullUpdatedAt_throwsException() {
        assertThrows(NullPointerException.class, () -> {
            Task.restore(5, "Test", TaskStatus.TODO, CREATED_AT, null);
        });
    }

    //Status Changes
    @Test
    void markInProgress_changesStatusToInProgress() {
        Task task = Task.create(5, "Status Test");

        task.markInProgress();

        assertEquals(TaskStatus.IN_PROGRESS, task.getStatus());
    }

    @Test
    void markDone_changesStatusToDone() {
        Task task = Task.create(10, "Status Test");

        task.markDone();

        assertEquals(TaskStatus.DONE, task.getStatus());
    }

    // Description Updates
    @Test
    void updateDescription_withValidText_changesDescription() {
        Task task = Task.create(10, "Description Test");

        task.updateDescription("Description Update");

        assertEquals("Description Update", task.getDescription());
    }

    @Test
    void updateDescription_withBlankText_throwsAndKeepsOriginal() {
        Task task = Task.create(10, "Test Task");

        assertThrows(IllegalArgumentException.class, () -> {
            task.updateDescription(" ");
        });

        assertEquals("Test Task", task.getDescription());
    }

    //Updates of Date and Time about last changes in the task
    @Test
    void markDone_updatesUpdatedAt() {
        Task task = Task.restore(5, "Test Task Restore", TaskStatus.IN_PROGRESS,
                CREATED_AT, OLD_UPDATED_AT);

        task.markDone();

        assertTrue(task.getUpdatedAt().isAfter(OLD_UPDATED_AT));
    }

    @Test
    void updateDescription_updatesUpdatedAt() {
        Task task = Task.restore(5, "Test Task Restore", TaskStatus.IN_PROGRESS,
                CREATED_AT, OLD_UPDATED_AT);

        task.updateDescription("Test Update");

        assertTrue(task.getUpdatedAt().isAfter(OLD_UPDATED_AT));
        assertEquals("Test Update", task.getDescription());
    }
}

