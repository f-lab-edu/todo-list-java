package com.example.todolistjava.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Todo {
    private String id;
    private String text;
    private boolean isCompleted;
    private boolean isEdit;

    public Todo() {
    }

    public Todo(String id, String text, boolean isCompleted, boolean isEdit) {
        this.id = id;
        this.text = text;
        this.isCompleted = isCompleted;
        this.isEdit = isEdit;
    }
}
