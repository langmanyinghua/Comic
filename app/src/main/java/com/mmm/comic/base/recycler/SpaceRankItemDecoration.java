package com.mmm.comic.base.recycler;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by link on 2018/6/22.
 */

public class SpaceRankItemDecoration extends RecyclerView.ItemDecoration {
    private int space;
    private int top;
    private int left;
    private int bottom;
    private int right;

    public SpaceRankItemDecoration(int space) {
        this.space = space;
    }

    public SpaceRankItemDecoration(int top, int left, int bottom, int right) {
        this.top = top;
        this.left = left;
        this.bottom = bottom;
        this.right = right;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = 0;
            outRect.left = 0;
            outRect.bottom = 0;
            outRect.right = 0;
        } else {
            if (space != 0) {
                outRect.top = space;
                outRect.left = space;
                outRect.bottom = space;
                outRect.right = space;
            } else {
                outRect.top = top;
                outRect.left = left;
                outRect.bottom = bottom;
                outRect.right = right;
            }
        }
    }
}
