package com.braiso_22.upkeep_app.model.vo.users;

import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Objects;

public abstract class User implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;


    private String login;

    private String code;

    private String identification;

    private String name;

    private String surnames;

    private String email;

    private String password;

    @Ignore
    public User() {

    }

    @Ignore
    public User(int id, @NotNull String login, @NotNull String code, @NotNull String identification, @NotNull String name,
                @NotNull String surnames, @NotNull String email) {
        this.id = id;
        this.login = login;
        this.code = code;
        this.identification = identification;
        this.name = name;
        this.surnames = surnames;
        this.email = email;
    }

    public User(@NotNull String login, @NotNull String code, @NotNull String identification, @NotNull String name,
                @NotNull String surnames, @NotNull String email) {
        this.login = login;
        this.code = code;
        this.identification = identification;
        this.name = name;
        this.surnames = surnames;
        this.email = email;
    }
    @Ignore
    public User(@NotNull String login, @NotNull String code, @NotNull String identification, @NotNull String name,
                @NotNull String surnames, @NotNull String email, @NotNull String password) {
        this.login = login;
        this.code = code;
        this.identification = identification;
        this.name = name;
        this.surnames = surnames;
        this.email = email;
        this.password = password;
    }

    @Ignore
    public User(String login, String password) {
        this.login = login;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format("{id=%d, login=%s,code=%s, identification=%s, name=%s, surnames=%s, email=%s }", id, login, code, identification, name, surnames, email);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getLogin().equals(user.getLogin());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLogin());
    }
}
