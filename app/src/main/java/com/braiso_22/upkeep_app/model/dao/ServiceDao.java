package com.braiso_22.upkeep_app.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.braiso_22.upkeep_app.model.vo.Service;

import java.util.List;

@Dao
public interface ServiceDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Service service);

    @Query("DELETE FROM service")
    void deleteAll();

    @Query("SELECT * FROM service")
    LiveData<List<Service>> getAll();

    @Update
    void update(Service service);
}
