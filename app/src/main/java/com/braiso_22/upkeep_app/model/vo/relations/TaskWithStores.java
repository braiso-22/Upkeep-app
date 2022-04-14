package com.braiso_22.upkeep_app.model.vo.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.braiso_22.upkeep_app.model.vo.Store;
import com.braiso_22.upkeep_app.model.vo.Task;

import java.util.List;

public class TaskWithStores {
    @Embedded
    public Task task;
    @Relation(
            parentColumn = "id",
            entityColumn = "task"
    )
    public List<Store> stores;
}
