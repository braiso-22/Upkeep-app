package com.braiso_22.upkeep_app.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.braiso_22.upkeep_app.model.vo.users.User;

import java.util.List;
@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(User user);

    @Query("DELETE FROM user")
    void deleteAll();

    @Query("DELETE FROM user where id = :id")
    void deleteById(int id);

    @Query("SELECT * FROM user")
    LiveData<List<User>> getAll();

    @Query("SELECT * FROM user where id = :id")
    LiveData<User> getById(int id);

    @Query("SELECT * FROM user WHERE login = :login")
    LiveData<List<User>> getByLogin(String login);

    @Update
    void update(User user);
}
