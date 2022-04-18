package com.braiso_22.upkeep_app.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

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

    @Query("SELECT * FROM task")
    LiveData<List<Task>> getAll();

    @Query("SELECT * FROM task where id = :id")
    LiveData<Task> getById(int id);

    @Update
    void update(Task task);
}
