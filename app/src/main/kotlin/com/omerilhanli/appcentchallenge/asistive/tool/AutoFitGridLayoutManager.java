package com.omerilhanli.appcentchallenge.asistive.tool;

import android.content.Context;
import android.content.res.Resources;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class AutoFitGridLayoutManager extends GridLayoutManager {

    private final static int COUNT = 2;

    private int columnWidth;
    private boolean columnWidthChanged = true;
    private Context mContext;

    public AutoFitGridLayoutManager(Context context) {
        super(context, COUNT);
        this.mContext = context;
        setColumnWidth(getScreenWidth() / COUNT);
    }

    private int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    private void setColumnWidth(int newColumnWidth) {
        if (newColumnWidth > 0 && newColumnWidth != columnWidth) {
            columnWidth = newColumnWidth;
            columnWidthChanged = true;
        }
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (columnWidthChanged && columnWidth > 0) {
            int totalSpace = getWidth() - getPaddingRight() - getPaddingLeft();
            int orientation = mContext.getResources().getConfiguration().orientation;
            int spanCount = Math.max(COUNT, totalSpace / columnWidth);
            if (orientation == ORIENTATION_LANDSCAPE) {
                spanCount++;
            }
            setSpanCount(spanCount);
            columnWidthChanged = false;
        }
        super.onLayoutChildren(recycler, state);
    }
}