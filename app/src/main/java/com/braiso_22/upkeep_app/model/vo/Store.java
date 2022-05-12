package com.braiso_22.upkeep_app.model.vo;

import androidx.room.Entity;
import androidx.room.Ignore;
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

    @NotNull
    private int task;

    @Ignore
    public Store(){}
    @Ignore
    public Store(int id, @NotNull String code, @NotNull String name, @NotNull String brand,
                 @NotNull String model, @NotNull String serialNumber, @NotNull String observations,
                 @NotNull int numStock, @NotNull int minStock, @NotNull int task) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.brand = brand;
        this.model = model;
        this.serialNumber = serialNumber;
        this.observations = observations;
        this.numStock = numStock;
        this.minStock = minStock;
        this.task = task;
    }

    public Store(@NotNull String code, @NotNull String name, @NotNull String brand,
                 @NotNull String model, @NotNull String serialNumber, @NotNull String observations,
                 @NotNull int numStock, @NotNull int minStock, @NotNull int task) {
        this.code = code;
        this.name = name;
        this.brand = brand;
        this.model = model;
        this.serialNumber = serialNumber;
        this.observations = observations;
        this.numStock = numStock;
        this.minStock = minStock;
        this.task = task;
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

    public int getTask() {
        return task;
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

    public void setNumStock(int numStock) {
        this.numStock = numStock;
    }

    public void setMinStock(int minStock) {
        this.minStock = minStock;
    }

    public void setTask(int task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return String.format("Store{id=%d }",id,code,name,brand,model,serialNumber,observations,numStock,minStock);
    }
}
