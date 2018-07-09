package com.sanket.simplecounterapp;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class List_Adapter extends ArrayAdapter {

    private List list = new ArrayList();

    List_Adapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    static class LayoutHandler
    {
        TextView id,name,count;

    }

    @Override
    public void add(@Nullable Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row = convertView;
        LayoutHandler layoutHandler;
        if(row ==  null)
        {
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.data_list,parent,false);
            layoutHandler = new LayoutHandler();
            layoutHandler.id = row.findViewById(R.id.id_view);
            layoutHandler.name = row.findViewById(R.id.name_view);
            layoutHandler.count = row.findViewById(R.id.count_view);

            row.setTag(layoutHandler);
        }
        else{
            layoutHandler = (LayoutHandler)row.getTag();

        }

        data_list dl = (data_list)this.getItem(position);
        assert dl != null;
        layoutHandler.id.setText(dl.getId());
        layoutHandler.name.setText(dl.getName());
        layoutHandler.count.setText(dl.getCount());


        if(position %2 == 1)
        {
            // Set a background color for ListView regular row/item
            row.setBackgroundColor(Color.parseColor("#FF8A80"));
        }
        else
        {
            // Set the background color for alternate row/item
            row.setBackgroundColor(Color.parseColor("#CCFF8A80"));
        }

        return row;

    }
}
