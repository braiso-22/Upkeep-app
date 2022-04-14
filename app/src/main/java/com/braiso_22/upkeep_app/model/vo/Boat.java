package com.braiso_22.upkeep_app.model.vo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "boat")
public class Boat {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NotNull
    private String code;
    @NotNull
    private String name;
    @NotNull
    private String registration;



    public Boat(@NotNull String code, @NotNull String name, @NotNull String registration) {
        this.code = code;
        this.name = name;
        this.registration = registration;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getRegistration() {
        return registration;
    }

    @Override
    public String toString() {
        return String.format("Boat{id=%d, code=%s, name=%s, registration=%s}",id,code,name,registration);
    }
}
