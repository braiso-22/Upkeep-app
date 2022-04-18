package com.braiso_22.upkeep_app.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.braiso_22.upkeep_app.model.vo.Upkeep;

import java.util.List;

@Dao
public interface UpkeepDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Upkeep upkeep);

    @Query("DELETE FROM upkeep")
    void deleteAll();

    @Query("DELETE FROM upkeep where id = :id")
    void deleteById(int id);

    @Query("SELECT * FROM upkeep")
    LiveData<List<Upkeep>> getAll();

    @Query("SELECT * FROM upkeep where id = :id")
    LiveData<Upkeep> getById(int id);

    @Update
    void update(Upkeep upkeep);
}
