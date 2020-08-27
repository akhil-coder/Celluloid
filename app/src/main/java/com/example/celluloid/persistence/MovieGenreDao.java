package com.example.celluloid.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.celluloid.requests.responses.Genre;

import java.util.List;

@Dao
public interface MovieGenreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertGenre(Genre... genre);

    @Query("SELECT * FROM genre")
    public LiveData<List<Genre>> loadAllGenre();
}
