package com.braiso_22.upkeep_app.vo.users;

import org.jetbrains.annotations.NotNull;

public class Owner extends User {


    public Owner(@NotNull String code, @NotNull String identification, @NotNull String name, @NotNull String surnames, @NotNull String email) {
        super(code, identification, name, surnames, email);
    }

    @Override
    public String toString() {
        return "Owner".concat(super.toString());
    }
}
