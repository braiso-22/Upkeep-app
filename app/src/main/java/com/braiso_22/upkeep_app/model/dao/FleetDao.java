package com.braiso_22.upkeep_app.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.braiso_22.upkeep_app.model.vo.Fleet;

import java.util.List;

@Dao
public interface FleetDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Fleet fleet);

    @Query("DELETE FROM fleet")
    void deleteAll();

    @Query("DELETE FROM fleet where id = :id")
    void deleteById(int id);

    @Query("SELECT * FROM fleet")
    LiveData<List<Fleet>> getAll();

    @Query("SELECT * FROM fleet where id = :id")
    LiveData<Fleet> getById(int id);

    @Update
    void update(Fleet fleet);
}
