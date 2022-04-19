package com.braiso_22.upkeep_app.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.braiso_22.upkeep_app.model.vo.Boat;

import java.util.List;

@Dao
public interface BoatDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Boat boat);

    @Query("DELETE FROM boat")
    void deleteAll();

    @Query("DELETE FROM boat WHERE id = :id")
    void deleteById(int id);

    @Query("SELECT * FROM boat")
    LiveData<List<Boat>> getAll();

    @Query("SELECT * FROM boat WHERE id = :id")
    LiveData<Boat> getById(int id);

    @Query("SELECT * FROM boat WHERE fleet = :fleet")
    LiveData<List<Boat>> getByFleet(int fleet);

    @Update
    void update(Boat boats);
}
