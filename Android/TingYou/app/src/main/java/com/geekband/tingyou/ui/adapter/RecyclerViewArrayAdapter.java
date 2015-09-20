package com.geekband.tingyou.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geekband.tingyou.R;
import com.geekband.tingyou.ui.listener.ProvinceClickListener;
import com.geekband.tingyou.ui.listener.ProvinceLongClickListener;

/**
 * Array Adapter for the RecyclerView on the main activity.
 */
public class RecyclerViewArrayAdapter extends RecyclerView.Adapter<RecyclerViewArrayAdapter.ViewHolder>{

    // RecyclerView ArrayAdapter implement by a set of String TextView
    private String[] dataSet;
    private ProvinceClickListener pClickListener;
    private ProvinceLongClickListener pLongClickListener;

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        private TextView textView;
        private ProvinceClickListener provinceClickListener;
        private ProvinceLongClickListener provinceLongClickListener;

        public ViewHolder(View v, ProvinceClickListener provinceClickListener, ProvinceLongClickListener provinceLongClickListener) {
            super(v);
            textView = (TextView)v.findViewById(R.id.item_in_recycler_view);
            v.setOnClickListener(this);
            v.setOnLongClickListener(this);
            this.provinceClickListener = provinceClickListener;
            this.provinceLongClickListener = provinceLongClickListener;
        }


        @Override
        public void onClick(View v) {
            if (provinceClickListener != null) {
                provinceClickListener.onProvinceClick(v, getAdapterPosition());
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (provinceLongClickListener != null) {
                provinceLongClickListener.onLongProvinceClick(v, getAdapterPosition());
            }

            return true;
        }
    }

    public RecyclerViewArrayAdapter(String[] dataSet, ProvinceClickListener pClickListener, ProvinceLongClickListener pLongClickListener) {
        this.dataSet = dataSet;
        this.pClickListener = pClickListener;
        this.pLongClickListener =  pLongClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_recycler_view, viewGroup, false);
        return new ViewHolder(view, pClickListener,pLongClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(dataSet[position]);
    }


    @Override
    public int getItemCount() {
        return dataSet.length;
    }

    private String[] getDataSet() {
        return dataSet;
    }
}
