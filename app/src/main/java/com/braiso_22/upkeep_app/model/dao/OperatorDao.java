package com.braiso_22.upkeep_app.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.braiso_22.upkeep_app.model.vo.Boat;
import com.braiso_22.upkeep_app.model.vo.users.Operator;

import java.util.List;

@Dao
public interface OperatorDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Operator operator);

    @Query("DELETE FROM operator")
    void deleteAll();

    @Query("DELETE FROM operator where id = :id")
    void deleteById(int id);

    @Query("SELECT * FROM operator")
    LiveData<List<Operator>> getAll();

    @Query("SELECT * FROM operator where id = :id")
    LiveData<Operator> getById(int id);

    @Query("SELECT * FROM operator WHERE login = :login")
    LiveData<Operator> getByLogin(String login);

    @Update
    void update(Operator operator);
}
