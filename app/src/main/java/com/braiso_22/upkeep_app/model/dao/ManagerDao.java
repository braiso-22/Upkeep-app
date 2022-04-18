package com.braiso_22.upkeep_app.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.braiso_22.upkeep_app.model.vo.users.Manager;

import java.util.List;

@Dao
public interface ManagerDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Manager manager);

    @Query("DELETE FROM manager")
    void deleteAll();

    @Query("DELETE FROM manager where id = :id")
    void deleteById(int id);

    @Query("SELECT * FROM manager")
    LiveData<List<Manager>> getAll();

    @Query("SELECT * FROM manager where id = :id")
    LiveData<Manager> getById(int id);

    @Update
    void update(Manager manager);
}
