package com.braiso_22.upkeep_app.model.vo.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.braiso_22.upkeep_app.model.vo.Task;
import com.braiso_22.upkeep_app.model.vo.Upkeep;

import java.util.List;

public class UpkeepWithTasks {
    @Embedded
    public Upkeep upkeep;
    @Relation(
            parentColumn = "id",
            entityColumn = "upkeep"
    )
    public List<Task> tasks;
}
