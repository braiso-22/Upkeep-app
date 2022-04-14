package com.braiso_22.upkeep_app.model.vo.users;

import androidx.room.Entity;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "operator")
public class Operator extends User {

    @NotNull
    private int service;

    public Operator(@NotNull String code, @NotNull String identification, @NotNull String name, @NotNull String surnames, @NotNull String email, @NotNull int service) {
        super(code, identification, name, surnames, email);
        this.service= service;
    }

    public int getService() {
        return service;
    }

    @Override
    public String toString() {
        return "Operator".concat(super.toString());
    }
}
