package com.example.vii_mook.recyclecardviewgrid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vii_mook.recyclecardviewgrid.R;
import com.example.vii_mook.recyclecardviewgrid.model.Animal;

import java.util.List;

/**
 * Created by vii-mook on 8/31/2017 AD.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private List<Animal> mData;
    private LayoutInflater mInflater;

    private static final String TAG = RecyclerAdapter.class.getSimpleName();

    public RecyclerAdapter(Context context, List<Animal> data) {
        this.mData = data;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        View view = mInflater.inflate(R.layout.list_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.d(TAG, "onCreateViewHolder" + position);

        Animal current = mData.get(position);
        holder.setData(current);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView imgThumb;
        int position;
        Animal current;

        public MyViewHolder(View itemview) {
            super(itemview);

            title = (TextView) itemview.findViewById(R.id.txv_row);
            imgThumb = (ImageView) itemview.findViewById(R.id.img_row);

        }

        public void setData(Animal current) {
            this.title.setText(current.getTitle());
            this.imgThumb.setImageResource(current.getImageId());

        }
    }
}

