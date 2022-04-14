package com.braiso_22.upkeep_app.model.vo.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.braiso_22.upkeep_app.model.vo.Service;
import com.braiso_22.upkeep_app.model.vo.users.Manager;

public class ServiceAndManager {
    @Embedded
    public Service service;
    @Relation(
            parentColumn = "id",
            entityColumn = "service"
    )
    public Manager manager;
}
