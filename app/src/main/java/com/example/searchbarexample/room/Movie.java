package com.example.searchbarexample.room;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movies")
public class Movie
{
    @PrimaryKey(autoGenerate = true)
    private int id;

    private final String movieName;
    private final String movieActors;
    private final String movieDescription;
    private final String movieReleaseDate;

    public Movie(String movieName, String movieActors, String movieDescription, String movieReleaseDate)
    {
        this.movieName = movieName;
        this.movieActors = movieActors;
        this.movieDescription = movieDescription;
        this.movieReleaseDate = movieReleaseDate;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }
    public String getMovieName()
    {
        return movieName;
    }
    public String getMovieActors()
    {
        return movieActors;
    }
    public String getMovieDescription()
    {
        return movieDescription;
    }
    public String getMovieReleaseDate()
    {
        return movieReleaseDate;
    }
    @NonNull
    @Override
    public String toString()
    {
        return movieName;
    }
}
