package com.example.searchbarexample;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.searchbarexample.room.Movie;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements CreateMovieDialogFragment.OnDialogClickListener
{
    private RecyclerView moviesRecyclerView;
    private FloatingActionButton fab;
    private MovieRecyclerViewAdapter adapter;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        moviesRecyclerView = findViewById(R.id.moviesRecyclerView);
        fab = findViewById(R.id.addNewMovieFab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                CreateMovieDialogFragment dialog = new CreateMovieDialogFragment();
                dialog.show(getSupportFragmentManager(), "CreateMovieDialogFragment");
            }
        });
        viewModel = new MainViewModel();
        viewModel.init(this);
        viewModel.getAllMovies().observe(this, new Observer<List<Movie>>()
        {
            @Override
            public void onChanged(List<Movie> movies)
            {
                adapter.updateMovies(movies);
            }
        });

        adapter = new MovieRecyclerViewAdapter(viewModel.getAllMovies().getValue());
        moviesRecyclerView.setAdapter(adapter);
        moviesRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        moviesRecyclerView.addItemDecoration(new MarginItemDecoration(16));
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT)
        {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target)
            {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction)
            {
                viewModel.deleteMovie(adapter.getMovie(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(moviesRecyclerView);
    }

    @Override
    public void onPositiveButtonClicked(DialogFragment dialog, Movie newMovie)
    {
        viewModel.insertMovie(newMovie);
    }

    @Override
    public void onNegativeButtonClicked(DialogFragment dialog)
    {
        //Does nothing in this example.
    }
}
