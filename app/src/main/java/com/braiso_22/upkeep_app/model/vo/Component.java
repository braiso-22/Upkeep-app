package com.braiso_22.upkeep_app.model.vo;

import androidx.room.Entity;
import androidx.room.Ignore;
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

    @NotNull
    private int service;
    @Ignore
    public Component(){}
    @Ignore
    public Component(int id, @NotNull String code, @NotNull String name, String brand, String model, String serialNumber, String observations, @NotNull int service) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.brand = brand;
        this.model = model;
        this.serialNumber = serialNumber;
        this.observations = observations;
        this.service = service;
    }

    public Component(@NotNull String code, @NotNull String name, String brand, String model, String serialNumber, String observations, @NotNull int service) {
        this.code = code;
        this.name = name;
        this.brand = brand;
        this.model = model;
        this.serialNumber = serialNumber;
        this.observations = observations;
        this.service = service;
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

    public int getService() {
        return service;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public void setService(int service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return String.format("Component{id=%d, code=%s, name=%s, brand=%s, model=%s, serialNumber=%s, observations=%s}");
    }
}
