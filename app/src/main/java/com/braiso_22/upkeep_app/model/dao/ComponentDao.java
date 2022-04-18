package com.braiso_22.upkeep_app.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.braiso_22.upkeep_app.model.vo.Component;

import java.util.List;

@Dao
public interface ComponentDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Component component);

    @Query("DELETE FROM component")
    void deleteAll();

    @Query("SELECT * FROM component")
    LiveData<List<Component>> getAll();

    @Query("SELECT * FROM component where id = :id")
    LiveData<Component> getById(int id);

    @Update
    void update(Component component);
}
