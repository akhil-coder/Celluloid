package com.example.celluloid.persistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.celluloid.requests.responses.genre.Genre;
import com.example.celluloid.requests.responses.movies.MovieDetails;

 @Database(entities = {Genre.class, MovieDetails.class}, version = 4)
 @TypeConverters({Converters.class})
 public abstract class TmDbDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "tmdb_database";
    public static TmDbDatabase instance;

    public static TmDbDatabase getInstance(final Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TmDbDatabase.class, DATABASE_NAME).fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    public abstract MovieGenreDao getMovieGenreDao();

    public abstract MovieDetailsDao getMovieDetailsDao();

 }
