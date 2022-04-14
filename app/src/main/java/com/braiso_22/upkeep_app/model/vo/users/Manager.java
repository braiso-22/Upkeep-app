package com.braiso_22.upkeep_app.model.vo.users;

import androidx.room.Entity;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "manager")
public class Manager extends User {

    public Manager(@NotNull String code, @NotNull String identification, @NotNull String name, @NotNull String surnames, @NotNull String email) {
        super(code, identification, name, surnames, email);
    }

    @Override
    public String toString() {
        return "Manager".concat(super.toString());
    }
}
