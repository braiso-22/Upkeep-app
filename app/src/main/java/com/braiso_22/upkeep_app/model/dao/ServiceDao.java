package com.braiso_22.upkeep_app.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.braiso_22.upkeep_app.model.vo.Boat;
import com.braiso_22.upkeep_app.model.vo.Service;

import java.util.List;

@Dao
public interface ServiceDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Service service);

    @Query("DELETE FROM service")
    void deleteAll();

    @Query("DELETE FROM service where id = :id")
    void deleteById(int id);

    @Query("SELECT * FROM service")
    LiveData<List<Service>> getAll();

    @Query("SELECT * FROM service where id = :id")
    LiveData<Service> getById(int id);

    @Query("SELECT * FROM service WHERE boat = :boat")
    LiveData<List<Service>> getByBoat(int boat);

    @Update
    void update(Service service);
}
