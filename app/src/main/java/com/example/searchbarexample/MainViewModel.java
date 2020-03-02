package com.example.searchbarexample;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.searchbarexample.room.Movie;

import java.util.List;

public class MainViewModel extends ViewModel
{
    private MovieRepository repo;

    public void init(Context context)
    {
        repo = new MovieRepository(context);
//        insertMovie(new Movie("Baywatch", "Dwayne Johnson, Zac Effron, and a bunch of hot chicks.", "When a dangerous crime wave hits the beach, the legendary Mitch Buchannon leads his elite squad of lifeguards on a mission to prove that you don't have to wear a badge to save the bay.", "2017"));
//        insertMovie(new Movie("Minions", "Kevin, Stuart, Bob", "Evolving from single-celled yellow organisms at the dawn of time, Minions live to serve, but find themselves working for a continual series of unsuccessful masters, from T. Rex to Napoleon. Without a master to grovel for, the Minions fall into a deep depression. But one minion, Kevin, has a plan; accompanied by his pals Stuart and Bob, Kevin sets forth to find a new evil boss for his brethren to follow. Their search leads them to Scarlet Overkill, the world's first-ever super-villainess.", "2015"));
//        insertMovie(new Movie("Toy Story 4", "Woody, Buzz, Lil Bo Peep", "Woody, Buzz Lightyear and the rest of the gang embark on a road trip with Bonnie and a new toy named Forky. The adventurous journey turns into an unexpected reunion as Woody's slight detour leads him to his long-lost friend Bo Peep. As Woody and Bo discuss the old days, they soon start to realize that they're worlds apart when it comes to what they want from life as a toy.", "2019"));
    }

    public LiveData<List<Movie>> getAllMovies()
    {
        return repo.getAllMovies();
    }

    public void insertMovie(Movie movie)
    {
        repo.insertMovie(movie);
    }

    public void updateMovie(Movie movie)
    {
        repo.updateMovie(movie);
    }

    public void deleteMovie(Movie movie)
    {
        repo.deleteMovie(movie);
    }
}
