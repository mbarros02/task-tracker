package com.mbarros.tasktracker.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskStatusTest {

    @Test
    void getValue_forTodo_returnsTodo() {
        assertEquals("todo", TaskStatus.TODO.getValue());
    }

    @Test
    void getValue_forInProgress_returnsInProgress() {
        assertEquals("in-progress", TaskStatus.IN_PROGRESS.getValue());
    }

    @Test
    void getValue_forDone_returnsDone() {
        assertEquals("done", TaskStatus.DONE.getValue());
    }

    @Test
    void fromValueShouldReturnTodo() {
        assertEquals(TaskStatus.TODO, TaskStatus.fromValue("todo"));
    }

    @Test
    void fromValueShouldReturnInProgress() {
        assertEquals(TaskStatus.IN_PROGRESS,
                TaskStatus.fromValue("in-progress"));
    }

    @Test
    void fromValueShouldReturnDone() {
        assertEquals(TaskStatus.DONE, TaskStatus.fromValue("done"));
    }

    @Test
    void fromValueShouldThrowExceptionForUppercaseTodo() {
        assertThrows(
                IllegalArgumentException.class,
                () -> TaskStatus.fromValue("TODO")
        );
    }

    @Test
    void fromValueShouldThrowExceptionForInvalidFormat() {
        assertThrows(
                IllegalArgumentException.class,
                () -> TaskStatus.fromValue("to-do")
        );
    }
}
