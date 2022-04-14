package com.braiso_22.upkeep_app.model.vo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "store")
public class Store {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NotNull
    private String code;
    @NotNull
    private String name;
    @NotNull
    private String brand;
    @NotNull
    private String model;
    @NotNull
    private String serialNumber;
    @NotNull
    private String observations;
    @NotNull
    private int numStock;
    @NotNull
    private int minStock;

    public Store(@NotNull String code, @NotNull String name, @NotNull String brand, @NotNull String model, @NotNull String serialNumber, @NotNull String observations, @NotNull int numStock, @NotNull int minStock) {
        this.code = code;
        this.name = name;
        this.brand = brand;
        this.model = model;
        this.serialNumber = serialNumber;
        this.observations = observations;
        this.numStock = numStock;
        this.minStock = minStock;
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

    public int getNumStock() {
        return numStock;
    }

    public int getMinStock() {
        return minStock;
    }

    @Override
    public String toString() {
        return String.format("Store{id=%d }",id,code,name,brand,model,serialNumber,observations,numStock,minStock);
    }
}
