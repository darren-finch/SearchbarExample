package com.example.searchbarexample;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.searchbarexample.room.Movie;

public class CreateMovieDialogFragment extends DialogFragment
{
    private OnDialogClickListener listener;
    private TextView movieNameEditText;
    private TextView movieDescriptionEditText;
    private TextView movieActorsEditText;
    private TextView movieReleaseDateEditText;

    @Override
    public void onAttach(@NonNull Context context)
    {
        super.onAttach(context);
        try
        {
            listener = (OnDialogClickListener) context;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(getActivity().toString()
                    + " must implement NoticeDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
    {
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View dialogView = inflater.inflate(R.layout.create_movie_dialog, null);
        movieNameEditText = dialogView.findViewById(R.id.movieNameEditText);
        movieDescriptionEditText = dialogView.findViewById(R.id.movieDescriptionEditText);
        movieActorsEditText = dialogView.findViewById(R.id.movieActorsEditText);
        movieReleaseDateEditText = dialogView.findViewById(R.id.movieReleaseDateEditText);
        builder.setMessage("Create New Movie")
                .setView(dialogView)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        if(movieNameEditText == null)
                            return;;
                        if(movieNameEditText.getText() == null || movieActorsEditText.getText() == null || movieDescriptionEditText.getText() == null || movieReleaseDateEditText.getText() == null)
                            return;;

                        Movie newMovie = new Movie(movieNameEditText.getText().toString(), movieActorsEditText.getText().toString(), movieDescriptionEditText.getText().toString(), movieReleaseDateEditText.getText().toString());
                        listener.onPositiveButtonClicked(CreateMovieDialogFragment.this, newMovie);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        listener.onNegativeButtonClicked(CreateMovieDialogFragment.this);
                        getDialog().cancel();
                    }
                });
        return builder.create();
    }

    public interface OnDialogClickListener
    {
        void onPositiveButtonClicked(DialogFragment dialog, Movie newMovie);
        void onNegativeButtonClicked(DialogFragment dialog);
    }
}
