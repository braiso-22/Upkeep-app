package com.braiso_22.upkeep_app.model.vo.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.braiso_22.upkeep_app.model.vo.Fleet;
import com.braiso_22.upkeep_app.model.vo.users.Owner;

import java.util.List;

public class OwnerWithFleets {
    @Embedded
    public Owner owner;
    @Relation(
            parentColumn = "id",
            entityColumn = "owner"
    )
    public List<Fleet> fleets;
}
