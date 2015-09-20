package com.geekband.tingyou.ui.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

import com.geekband.tingyou.R;

/**
 * CursorAdapter for the searching activity
 */
public class SearchCursorAdapter extends CursorAdapter {


    public SearchCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_detail_recycler_view, parent, false);

        return null;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

    }
}
