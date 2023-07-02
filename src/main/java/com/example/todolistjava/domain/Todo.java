package com.example.todolistjava.domain;

public class Todo {
    private String id;
    private String text;
    private boolean isCompleted;

    public Todo(String id, String text, boolean isCompleted) {
        this.id = id;
        this.text = text;
        this.isCompleted = isCompleted;
    }
}
