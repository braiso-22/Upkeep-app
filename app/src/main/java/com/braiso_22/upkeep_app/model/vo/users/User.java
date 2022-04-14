package com.braiso_22.upkeep_app.model.vo.users;

import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @NotNull
    private String code;
    @NotNull
    private String identification;
    @NotNull
    private String name;
    @NotNull
    private String surnames;
    @NotNull
    private String email;

    public User(@NotNull String code, @NotNull String identification, @NotNull String name, @NotNull String surnames, @NotNull String email) {
        this.code = code;
        this.identification = identification;
        this.name = name;
        this.surnames = surnames;
        this.email = email;
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

    public String getIdentification() {
        return identification;
    }

    public String getName() {
        return name;
    }

    public String getSurnames() {
        return surnames;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return String.format("{id=%d, code=%s, identification=%s, name=%s, surnames=%s, email=%s }", id, code, identification, name, surnames, email);
    }
}
