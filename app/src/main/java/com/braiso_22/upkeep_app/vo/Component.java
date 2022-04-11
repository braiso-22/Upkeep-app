package com.braiso_22.upkeep_app.vo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "component")
public class Component {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NotNull
    private String code;
    @NotNull
    private String name;
    private String brand;
    private String model;
    private String serialNumber;
    private String observations;

    public Component(@NotNull String code, @NotNull String name, String brand, String model, String serialNumber, String observations) {
        this.code = code;
        this.name = name;
        this.brand = brand;
        this.model = model;
        this.serialNumber = serialNumber;
        this.observations = observations;
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

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getObservations() {
        return observations;
    }

    @Override
    public String toString() {
        return String.format("Component{id=%d, code=%s, name=%s, brand=%s, model=%s, serialNumber=%s, observations=%s}");
    }
}
