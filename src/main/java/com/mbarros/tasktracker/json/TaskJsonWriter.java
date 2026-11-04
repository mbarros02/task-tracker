package com.mbarros.tasktracker.json;

import com.mbarros.tasktracker.domain.Task;

import java.util.List;

public class TaskJsonWriter {

    public String serialize(List<Task> tasks) {
        if (tasks.isEmpty()) {
            return "[]";
        }

        StringBuilder json = new StringBuilder();
        json.append("[\n");

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);

            json.append("  {\n");

            json.append("    \"id\": ");
            json.append(task.getId());
            json.append(",\n");

            json.append("    \"description\": \"");
            json.append(escape(task.getDescription()));
            json.append("\",\n");

            json.append("    \"status\": \"");
            json.append(task.getStatus().getValue());
            json.append("\",\n");

            json.append("    \"createdAt\": \"");
            json.append(task.getCreatedAt().toString());
            json.append("\",\n");

            json.append("    \"updatedAt\": \"");
            json.append(task.getUpdatedAt().toString());
            json.append("\"\n");

            json.append("  }");

            if (i < tasks.size() - 1) {
                json.append(",");
            }

            json.append("\n");
        }

        json.append("]");

        return json.toString();
    }

    private String escape(String value) {
        return value
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }
}
