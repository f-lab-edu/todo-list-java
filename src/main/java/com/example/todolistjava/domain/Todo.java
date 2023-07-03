package com.example.todolistjava.domain;

public class Todo {
    private String id;
    private String text;
    private boolean isCompleted;
    private boolean isEdit;

    public Todo(String id, String text, boolean isCompleted, boolean isEdit) {
        this.id = id;
        this.text = text;
        this.isCompleted = isCompleted;
        this.isEdit = isEdit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public boolean isEdit() {
        return isEdit;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
    }
}
