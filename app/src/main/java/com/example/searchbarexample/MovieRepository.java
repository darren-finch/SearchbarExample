package com.example.searchbarexample;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.searchbarexample.room.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MovieRepository
{
    private MovieDao dao;

    public MovieRepository(Context context)
    {
        dao = MovieDatabase.getInstance(context).getDao();
    }

    public LiveData<List<Movie>> getAllMovies()
    {
        GetAllMoviesTask task = new GetAllMoviesTask();
        try
        {
            return task.execute().get();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public void insertMovie(Movie movie)
    {
        new InsertMovieTask().execute(movie);
    }

    public void updateMovie(Movie movie)
    {
        new UpdateMovieTask().execute(movie);
    }

    public void deleteMovie(Movie movie)
    {
        new DeleteMovieTask().execute(movie);
    }

    private class GetAllMoviesTask extends AsyncTask<Void, Void, LiveData<List<Movie>>>
    {
        @Override
        protected LiveData<List<Movie>> doInBackground(Void... voids)
        {
            return dao.getAllMovies();
        }
    }

    private class InsertMovieTask extends AsyncTask<Movie, Void, Void>
    {
        @Override
        protected Void doInBackground(Movie... movies)
        {
            dao.insertMovie(movies[0]);
            return null;
        }
    }

    private class UpdateMovieTask extends AsyncTask<Movie, Void, Void>
    {
        @Override
        protected Void doInBackground(Movie... movies)
        {
            dao.updateMovie(movies[0]);
            return null;
        }
    }

    private class DeleteMovieTask extends AsyncTask<Movie, Void, Void>
    {
        @Override
        protected Void doInBackground(Movie... movies)
        {
            dao.deleteMovie(movies[0]);
            return null;
        }
    }
}
