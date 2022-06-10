package com.braiso_22.upkeep_app.model.vo.users;

import android.graphics.Path;

import androidx.room.Entity;
import androidx.room.Ignore;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "operator")
public class Operator extends User {

    @NotNull
    private int service;
    @NotNull
    private int owner;

    @Ignore
    public Operator() {
    }

    @Ignore
    public Operator(int id, @NotNull String login, @NotNull String code, @NotNull String identification, @NotNull String name,
                    @NotNull String surnames, @NotNull String email, @NotNull int service, @NotNull int owner) {
        super(id, login, code, identification, name, surnames, email);
        this.service = service;
        this.owner = owner;
    }

    public Operator(@NotNull String login, @NotNull String code, @NotNull String identification, @NotNull String name, @NotNull String surnames, @NotNull String email, @NotNull int service, @NotNull int owner) {
        super(login, code, identification, name, surnames, email);
        this.service = service;
        this.owner = owner;
    }

    @Ignore
    public Operator(@NotNull String login, @NotNull String password) {
        super(login, password);
    }

    public int getService() {
        return service;
    }

    public void setService(int service) {
        this.service = service;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Operator".concat(super.toString());
    }
}
