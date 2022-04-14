package com.braiso_22.upkeep_app.model.vo.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.braiso_22.upkeep_app.model.vo.Boat;
import com.braiso_22.upkeep_app.model.vo.Fleet;

import java.util.List;

public class FleetWithBoats {
    @Embedded
    public Fleet fleet;
    @Relation(
            parentColumn = "id",
            entityColumn = "fleet"
    )
    public List<Boat> boats;
}
