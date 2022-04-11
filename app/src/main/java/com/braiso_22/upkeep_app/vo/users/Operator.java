package com.braiso_22.upkeep_app.vo.users;

import androidx.room.Entity;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "operator")
public class Operator extends User {

    public Operator(@NotNull String code, @NotNull String identification, @NotNull String name, @NotNull String surnames, @NotNull String email) {
        super(code, identification, name, surnames, email);
    }

    @Override
    public String toString() {
        return "Operator".concat(super.toString());
    }
}
