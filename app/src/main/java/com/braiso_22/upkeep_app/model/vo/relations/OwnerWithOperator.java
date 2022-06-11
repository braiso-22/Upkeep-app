package com.braiso_22.upkeep_app.model.vo.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.braiso_22.upkeep_app.model.vo.users.Operator;
import com.braiso_22.upkeep_app.model.vo.users.Owner;

import java.util.List;

public class OwnerWithOperator {
    @Embedded
    public Owner owner;
    @Relation(
            parentColumn = "id",
            entityColumn = "owner"
    )
    public List<Operator> operator;
}
