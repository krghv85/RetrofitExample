package com.metroinfrasys.retrofitexample.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.metroinfrasys.retrofitexample.R;
import com.metroinfrasys.retrofitexample.model.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Metro on 14-07-2017.
 */

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ItemHolder> {
    private List<Item> listItems = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private Context context;

    public RecycleViewAdapter(Context context, List<Item> listItems) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.listItems = listItems;

    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.recycleview_row, parent, false);
        ItemHolder myViewHolder = new ItemHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final ItemHolder holder, final int position) {

        holder.textView1.setText(listItems.get(position).getScore() + "");
        holder.textView2.setText(listItems.get(position).getAnswerId() + "");


    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }





    public class ItemHolder extends RecyclerView.ViewHolder {

        private View view;
        TextView textView1;
        TextView textView2;

        public ItemHolder(View cView) {
            super(cView);
            view = cView;
            textView1 = (TextView) view.findViewById(R.id.text1);
            textView2 = (TextView) view.findViewById(R.id.text2);


        }
    }
}
