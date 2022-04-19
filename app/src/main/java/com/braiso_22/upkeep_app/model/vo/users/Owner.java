package com.braiso_22.upkeep_app.model.vo.users;

import androidx.room.Entity;
import androidx.room.Ignore;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "owner")
public class Owner extends User {

    @Ignore
    public Owner() {
    }
    @Ignore
    public Owner(int id,@NotNull String login, @NotNull String code, @NotNull String identification, @NotNull String name, @NotNull String surnames, @NotNull String email) {
        super(id,login, code, identification, name, surnames, email);
    }

    public Owner(@NotNull String login,@NotNull String code, @NotNull String identification, @NotNull String name, @NotNull String surnames, @NotNull String email) {
        super(login, code, identification, name, surnames, email);
    }

    @Override
    public String toString() {
        return "Owner".concat(super.toString());
    }
}
