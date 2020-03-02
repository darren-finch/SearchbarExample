package com.example.searchbarexample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.searchbarexample.room.Movie;

import java.util.ArrayList;
import java.util.List;

//SECTION 2: Creating The Adapter
public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.MovieViewHolder> implements Filterable
{
    private List<Movie> moviesDisplay;
    private List<Movie> moviesData;

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
        moviesData = movies;
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter()
    {
        return new Filter()
        {
            @Override
            protected FilterResults performFiltering(CharSequence constraint)
            {
                FilterResults results = new FilterResults();
                String lowerCaseQuery = constraint.toString().toLowerCase();
                ArrayList<Movie> moviesQueryList = new ArrayList<>();
                for(Movie movie : MovieRecyclerViewAdapter.this.moviesData)
                {
                    if (movie.getMovieName().toLowerCase().contains(lowerCaseQuery) ||
                            movie.getMovieActors().toLowerCase().contains(lowerCaseQuery) ||
                            movie.getMovieDescription().toLowerCase().contains(lowerCaseQuery) ||
                            movie.getMovieReleaseDate().toLowerCase().contains(lowerCaseQuery))
                    {
                        moviesQueryList.add(movie);
                    }
                }
                results.values = moviesQueryList;
                results.count = moviesQueryList.size();
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results)
            {
                moviesDisplay = (ArrayList<Movie>) results.values;
                notifyDataSetChanged();
            }
        };
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
