package com.braiso_22.upkeep_app.model.vo;

import androidx.room.Entity;
import androidx.room.Ignore;
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
    @NotNull
    private int fleet;
    @Ignore
    public Boat(){}
    @Ignore
    public Boat(int id, @NotNull String code, @NotNull String name, @NotNull String registration, @NotNull int fleet) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.registration = registration;
        this.fleet = fleet;
    }

    public Boat(@NotNull String code, @NotNull String name, @NotNull String registration, @NotNull int fleet) {
        this.code = code;
        this.name = name;
        this.registration = registration;
        this.fleet = fleet;
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

    public int getFleet() {
        return fleet;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public void setFleet(int fleet) {
        this.fleet = fleet;
    }

    @Override
    public String toString() {
        return String.format("Boat{id=%d, code=%s, name=%s, registration=%s}", id, code, name, registration);
    }
}
