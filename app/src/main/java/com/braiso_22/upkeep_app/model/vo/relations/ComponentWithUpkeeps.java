package com.braiso_22.upkeep_app.model.vo.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.braiso_22.upkeep_app.model.vo.Component;
import com.braiso_22.upkeep_app.model.vo.Upkeep;

import java.util.List;

public class ComponentWithUpkeeps {
    @Embedded
    public Component component;
    @Relation(
            parentColumn = "id",
            entityColumn = "component"
    )
    public List<Upkeep> upkeeps;
}
