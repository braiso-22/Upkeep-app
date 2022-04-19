package com.braiso_22.upkeep_app.model.vo.users;

import android.graphics.Path;

import androidx.room.Entity;
import androidx.room.Ignore;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "operator")
public class Operator extends User {

    @NotNull
    private int service;

    @Ignore
    public Operator(){}
    @Ignore
    public Operator(int id,@NotNull String login, @NotNull String code, @NotNull String identification, @NotNull String name,
                    @NotNull String surnames, @NotNull String email, @NotNull int service) {
        super(id, login, code, identification, name, surnames, email);
        this.service = service;
    }

    public Operator(@NotNull String login,@NotNull String code, @NotNull String identification, @NotNull String name, @NotNull String surnames, @NotNull String email, @NotNull int service) {
        super(login, code, identification, name, surnames, email);
        this.service= service;
    }

    public int getService() {
        return service;
    }

    public void setService(int service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return "Operator".concat(super.toString());
    }
}
