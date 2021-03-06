package com.braiso_22.upkeep_app.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.braiso_22.upkeep_app.model.vo.Boat;
import com.braiso_22.upkeep_app.model.vo.Store;

import java.util.List;

@Dao
public interface StoreDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Store store);

    @Query("DELETE FROM store")
    void deleteAll();

    @Query("DELETE FROM store where id = :id")
    void deleteById(int id);

    @Query("DELETE FROM store where task = :taskId")
    void deleteByTask(int taskId);

    @Query("SELECT * FROM store")
    LiveData<List<Store>> getAll();

    @Query("SELECT * FROM store where id = :id")
    LiveData<Store> getById(int id);

    @Query("SELECT * FROM store WHERE task = :task")
    LiveData<List<Store>> getByTask(int task);

    @Update
    void update(Store store);
}
