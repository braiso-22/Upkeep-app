package com.braiso_22.upkeep_app.model.vo.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.braiso_22.upkeep_app.model.vo.users.Operator;
import com.braiso_22.upkeep_app.model.vo.Task;

import java.util.List;

public class OperatorWithTasks {
    @Embedded
    public Operator operator;
    @Relation(
            parentColumn = "id",
            entityColumn = "operator"
    )
    public List<Task> tasks;
}
