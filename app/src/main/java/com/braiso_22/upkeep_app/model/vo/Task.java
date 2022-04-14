package com.braiso_22.upkeep_app.model.vo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "task")
public class Task {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NotNull
    private int length;
    @NotNull
    private String description;

    public Task(@NotNull int length, @NotNull String description) {
        this.length = length;
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getLenght() {
        return length;
    }
    @NotNull
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("Task{id=%d}, length=%s, description=%s", id, length, description);
    }
}
