package com.braiso_22.upkeep_app.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.braiso_22.upkeep_app.model.vo.Boat;
import com.braiso_22.upkeep_app.model.vo.Task;

import java.util.List;

@Dao
public interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Task task);

    @Query("DELETE FROM task")
    void deleteAll();

    @Query("DELETE FROM task where id = :id")
    void deleteById(int id);

    @Query("DELETE FROM task where upkeep = :upkeepId")
    void deleteByUpkeep(int upkeepId);

    @Query("SELECT * FROM task")
    LiveData<List<Task>> getAll();

    @Query("SELECT * FROM task where id = :id")
    LiveData<Task> getById(int id);

    @Query("SELECT * FROM task WHERE upkeep = :upkeep")
    LiveData<List<Task>> getByUpkeep(int upkeep);

    @Query("SELECT * FROM task WHERE operator = :operator")
    LiveData<List<Task>> getByOperator(int operator);

    @Update
    void update(Task task);
}
