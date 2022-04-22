package com.braiso_22.upkeep_app.model.vo.users;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;
@Entity(tableName = "user")
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NotNull
    private String login;
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
    @Ignore
    public User() {

    }
    @Ignore
    public User(int id, @NotNull String login, @NotNull String code, @NotNull String identification, @NotNull String name,
                @NotNull String surnames, @NotNull String email) {
        this.id = id;
        this.code = code;
        this.identification = identification;
        this.name = name;
        this.surnames = surnames;
        this.email = email;
    }

    public User(@NotNull String login, @NotNull String code, @NotNull String identification, @NotNull String name,
                @NotNull String surnames, @NotNull String email) {
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

    public void setCode(String code) {
        this.code = code;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return String.format("{id=%d, code=%s, identification=%s, name=%s, surnames=%s, email=%s }", id, code, identification, name, surnames, email);
    }
}
