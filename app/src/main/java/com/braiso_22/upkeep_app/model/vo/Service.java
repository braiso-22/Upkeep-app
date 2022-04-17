package com.braiso_22.upkeep_app.model.vo;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "service")
public class Service {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NotNull
    private String code;
    @NotNull
    private String name;
    @NotNull
    private int boat;

    @Ignore
    public Service(){}
    @Ignore
    public Service(int id, @NotNull String code, @NotNull String name, @NotNull int boat) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.boat = boat;
    }

    public Service(@NotNull String code, @NotNull String name, @NotNull int boat) {
        this.code = code;
        this.name = name;
        this.boat = boat;
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

    public int getBoat() {
        return boat;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBoat(int boat) {
        this.boat = boat;
    }

    @Override
    public String toString() {
        return String.format("Service{id=%d, code=%s, name=%s}", id, code, name);
    }
}
