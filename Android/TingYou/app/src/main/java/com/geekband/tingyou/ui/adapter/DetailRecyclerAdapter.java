package com.geekband.tingyou.ui.adapter;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geekband.tingyou.Env;
import com.geekband.tingyou.R;
import com.geekband.tingyou.ui.listener.DetailClickListener;
import com.geekband.tingyou.ui.listener.DetailLongClickListener;

import java.util.ArrayList;

/**
 *
 */
public class DetailRecyclerAdapter extends RecyclerView.Adapter<DetailRecyclerAdapter.ViewHolder>{

    private String[] dataSet = null;
    private boolean isCity;
    private DetailClickListener dClickListener;
    private DetailLongClickListener dLongClickListener;

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        private TextView textView;
        private DetailClickListener detailClickListener;
        private DetailLongClickListener detailLongClickListener;

        public ViewHolder(View v, DetailClickListener detailClickListener, DetailLongClickListener detailLongClickListener) {
            super(v);

            // TODO: set the textView of RecyclerView due to isCity.
            // TODO: detail of view for scene to be further implement.
            if (isCity) {
                textView = (TextView) v.findViewById(R.id.item_in_detail);
            } else {
                textView = (TextView) v.findViewById(R.id.item_in_detail);
            }

            this.detailClickListener = detailClickListener;
            this.detailLongClickListener = detailLongClickListener;
        }


        @Override
        public void onClick(View v) {
            if (detailClickListener != null) {
                detailClickListener.onDetailClick(v, getAdapterPosition());
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (detailLongClickListener != null) {
                detailLongClickListener.onDetailLongClick(v, getAdapterPosition());
            }
            return true;
        }
    }

    public DetailRecyclerAdapter(String[] dataSet, boolean isCity, DetailClickListener dClickListener, DetailLongClickListener dLongClickListener) {
        // TODO: set the dataSet to city name group or scene name group according to isCity.

        this.isCity = isCity;
        this.dataSet = dataSet;
        // TODO: dataSet = ...;

        this.dClickListener = dClickListener;
        this.dLongClickListener = dLongClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_detail_recycler_view, viewGroup, false);
        return new ViewHolder(view, dClickListener, dLongClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(dataSet[position]);
    }


    @Override
    public int getItemCount() {
        return dataSet.length;
    }
}
