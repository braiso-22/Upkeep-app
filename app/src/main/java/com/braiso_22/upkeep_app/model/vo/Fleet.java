package com.braiso_22.upkeep_app.model.vo;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


import org.jetbrains.annotations.NotNull;

@Entity(tableName = "fleet")
public class Fleet {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @NotNull
    private String name;
    @NotNull
    private int onwer;

    @Ignore
    public Fleet(){

    }
    @Ignore
    public Fleet(int id, @NotNull String name, @NotNull int onwer) {
        this.id = id;
        this.name = name;
        this.onwer = onwer;
    }

    public Fleet(@NotNull String name, int onwer) {
        this.name = name;
        this.onwer = onwer;
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

    public int getOnwer() {
        return onwer;
    }

    public void setOnwer(int onwer) {
        this.onwer = onwer;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Fleet{id=%d, name=%s }",id,name);
    }
}
