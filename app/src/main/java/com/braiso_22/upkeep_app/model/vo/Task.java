package com.braiso_22.upkeep_app.model.vo;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Entity(tableName = "task")
public class Task implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NotNull
    private int length;
    @NotNull
    private String name;
    @NotNull
    private String description;

    @NotNull
    private int upkeep;
    @NotNull
    private int operator;

    @Ignore
    public Task() {
    }

    @Ignore
    public Task(int id, @NotNull int length, @NotNull String name, @NotNull String description, @NotNull int upkeep, @NotNull int operator) {
        this.id = id;
        this.length = length;
        this.name = name;
        this.description = description;
        this.upkeep = upkeep;
        this.operator = operator;
    }

    public Task(@NotNull int length, @NotNull String name, @NotNull String description, @NotNull int upkeep, @NotNull int operator) {
        this.length = length;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLength(int length) {
        this.length = length;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUpkeep(int upkeep) {
        this.upkeep = upkeep;
    }

    public void setOperator(int operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return String.format("Task{id=%d}, length=%s, description=%s", id, length, description);
    }
}
