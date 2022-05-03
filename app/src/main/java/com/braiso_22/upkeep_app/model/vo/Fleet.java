package com.braiso_22.upkeep_app.model.vo;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Entity(tableName = "fleet")
public class Fleet implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @NotNull
    private String name;
    @NotNull
    private int owner;

    @Ignore
    public Fleet(){

    }
    @Ignore
    public Fleet(int id, @NotNull String name, @NotNull int owner) {
        this.id = id;
        this.name = name;
        this.owner = owner;
    }

    public Fleet(@NotNull String name, int owner) {
        this.name = name;
        this.owner = owner;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Fleet{id=%d, name=%s }",id,name);
    }
}
