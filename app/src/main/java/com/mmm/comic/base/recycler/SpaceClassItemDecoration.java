package com.mmm.comic.base.recycler;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Created by link on 2018/6/22.
 */

public class SpaceClassItemDecoration extends RecyclerView.ItemDecoration {
    private int space;
    private int spanCount;

    public SpaceClassItemDecoration(int space, int spanCount) {
        this.space = space;
        this.spanCount = spanCount;
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (space != 0) {
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.left = 0;
                outRect.right = 0;
            } else {
                if (parent.getChildAdapterPosition(view) % spanCount == 0) {
                    outRect.left = space;
                    outRect.right = 0;
                } else if (parent.getChildAdapterPosition(view) % spanCount == 1) {
                    outRect.left = 0;
                    outRect.right = space;
                } else {
                    outRect.left = space / 2;
                    outRect.right = space / 2;
                }
            }
        }
    }
}
