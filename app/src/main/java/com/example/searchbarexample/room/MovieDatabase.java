package com.example.searchbarexample.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = { Movie.class }, version = 2, exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase
{
    private static MovieDatabase instance;
    public abstract MovieDao getDao();
    public static MovieDatabase getInstance(Context context)
    {
        if(instance == null)
        {
            instance = Room.databaseBuilder(context, MovieDatabase.class, "movies")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
