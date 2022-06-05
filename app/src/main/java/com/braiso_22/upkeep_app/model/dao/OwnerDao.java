package com.braiso_22.upkeep_app.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.braiso_22.upkeep_app.model.vo.Boat;
import com.braiso_22.upkeep_app.model.vo.users.Owner;

import java.util.List;

@Dao
public interface OwnerDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Owner owner);

    @Query("DELETE FROM owner")
    void deleteAll();

    @Query("DELETE FROM owner where id = :id")
    void deleteById(int id);

    @Query("SELECT * FROM owner")
    LiveData<List<Owner>> getAll();

    @Query("SELECT * FROM owner where id = :id")
    LiveData<Owner> getById(int id);

    @Query("SELECT * FROM owner WHERE login = :login")
    LiveData<Owner> getByLogin(String login);

    @Update
    void update(Owner owner);
}
