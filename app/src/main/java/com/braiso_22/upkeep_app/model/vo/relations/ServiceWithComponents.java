package com.braiso_22.upkeep_app.model.vo.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.braiso_22.upkeep_app.model.vo.Component;
import com.braiso_22.upkeep_app.model.vo.Service;

import java.util.List;

public class ServiceWithComponents {
    @Embedded
    public Service service;
    @Relation(
            parentColumn = "id",
            entityColumn = "service"
    )
    public List<Component> components;
}
