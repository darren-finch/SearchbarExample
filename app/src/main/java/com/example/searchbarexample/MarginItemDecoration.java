package com.example.searchbarexample;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MarginItemDecoration extends RecyclerView.ItemDecoration
{
    private int margin;
    public MarginItemDecoration(int margin)
    {
        this.margin = margin;
    }
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state)
    {
//        super.getItemOffsets(outRect, view, parent, state);
        if(parent.getChildAdapterPosition(view) == 0)
            outRect.top = margin;

        outRect.left = margin;
        outRect.bottom = margin;
        outRect.right = margin;
    }
}
