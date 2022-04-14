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

    @NotNull
    private int upkeep;
    @NotNull
    private int operator;

    public Task(@NotNull int length, @NotNull String description, @NotNull int upkeep, @NotNull int operator) {
        this.length = length;
        this.description = description;
        this.upkeep = upkeep;
        this.operator = operator;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setLength(int length){
        this.length=length;
    }
    public int getLength() {
        return length;
    }

    @NotNull
    public String getDescription() {
        return description;
    }

    public int getUpkeep() {
        return upkeep;
    }

    public int getOperator() {
        return operator;
    }

    @Override
    public String toString() {
        return String.format("Task{id=%d}, length=%s, description=%s", id, length, description);
    }
}
