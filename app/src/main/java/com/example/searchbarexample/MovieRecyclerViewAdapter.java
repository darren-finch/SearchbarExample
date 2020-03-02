package com.example.searchbarexample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.searchbarexample.room.Movie;

import java.util.List;

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.MovieViewHolder>// implements Filterable
{
    private List<Movie> moviesDisplay;

    MovieRecyclerViewAdapter(List<Movie> movies)
    {
        this.moviesDisplay = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View movieCardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card, parent, false);
        return new MovieViewHolder(movieCardView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position)
    {
        Movie movie = moviesDisplay.get(position);
        holder.movieName.setText(movie.getMovieName());
        holder.movieActors.setText(movie.getMovieActors());
        holder.movieDescription.setText(movie.getMovieDescription());
        holder.movieReleaseDate.setText(movie.getMovieReleaseDate());
    }

    @Override
    public int getItemCount()
    {
        return moviesDisplay == null ? 0 : moviesDisplay.size();
    }

    Movie getMovie(int index)
    {
        return moviesDisplay.get(index);
    }

    void updateMovies(List<Movie> movies)
    {
        moviesDisplay = movies;
        notifyDataSetChanged();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder
    {
        private TextView movieName;
        private TextView movieActors;
        private TextView movieDescription;
        private TextView movieReleaseDate;

        MovieViewHolder(@NonNull View itemView)
        {
            super(itemView);
            movieName = itemView.findViewById(R.id.movieName);
            movieActors = itemView.findViewById(R.id.movieActors);
            movieDescription = itemView.findViewById(R.id.movieDescription);
            movieReleaseDate = itemView.findViewById(R.id.movieReleaseDate);
        }
    }
}
