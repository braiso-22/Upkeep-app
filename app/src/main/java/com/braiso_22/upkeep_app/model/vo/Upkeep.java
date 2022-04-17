package com.braiso_22.upkeep_app.model.vo;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "upkeep")
public class Upkeep {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @NotNull
    private String date;
    @NotNull
    private String hour;

    @NotNull
    private int component;

    @Ignore
    public Upkeep(){}
    @Ignore
    public Upkeep(int id, @NotNull String date, @NotNull String hour, @NotNull int component) {
        this.id = id;
        this.date = date;
        this.hour = hour;
        this.component = component;
    }

    public Upkeep(@NotNull String date, @NotNull String hour, @NotNull int component) {
        this.date = date;
        this.hour = hour;
        this.component = component;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getHour() {
        return hour;
    }

    public int getComponent() {
        return component;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setComponent(int component) {
        this.component = component;
    }

    @Override
    public String toString() {
        return String.format("Upkeep{id=%d, date=%s, hour=%s}", id, date, hour);
    }
}
