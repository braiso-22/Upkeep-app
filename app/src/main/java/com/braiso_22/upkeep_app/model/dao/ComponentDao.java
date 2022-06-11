package com.braiso_22.upkeep_app.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.braiso_22.upkeep_app.model.vo.Boat;
import com.braiso_22.upkeep_app.model.vo.Component;

import java.util.List;

@Dao
public interface ComponentDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Component component);

    @Query("DELETE FROM component")
    void deleteAll();

    @Query("DELETE FROM component where id = :id")
    void deleteById(int id);

    @Query("DELETE FROM component where service = :serviceId")
    void deleteByService(int serviceId);

    @Query("SELECT * FROM component")
    LiveData<List<Component>> getAll();

    @Query("SELECT * FROM component where id = :id")
    LiveData<Component> getById(int id);

    @Query("SELECT * FROM component WHERE service = :service")
    LiveData<List<Component>> getByService(int service);

    @Update
    void update(Component component);
}
