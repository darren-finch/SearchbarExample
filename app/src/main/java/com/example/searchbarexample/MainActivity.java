package com.example.searchbarexample;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.SearchView;

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
//    SECTION 3: Adding Querying to Adapter
//    private String curQuery = "";

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
//                SECTION 3: Adding Queries to The Adapter
//                adapter.getFilter().filter(curQuery);
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

//    SECTION 2: Setup Search View And Listen For Queries
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu)
//    {
//        getMenuInflater().inflate(R.menu.search_menu, menu);
//        SearchView searchView = (SearchView) menu.findItem(R.id.searchButton).getActionView();
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
//        {
//            @Override
//            public boolean onQueryTextSubmit(String query)
//            {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText)
//            {
////                SECTION 3: Adding Queries to The Adapter
////                curQuery = newText;
////                adapter.getFilter().filter(curQuery);
//                if(curQuery.isEmpty())
//                    fab.setVisibility(View.VISIBLE);
//                else
//                    fab.setVisibility(View.GONE);
//
//                moviesRecyclerView.scrollToPosition(0);
//                return false;
//            }
//        });
//        return true;
//    }

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
