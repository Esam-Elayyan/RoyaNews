package com.example.esam.recyclerviewproject;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Esam on 21/08/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<ListItem> listItems;
    private Context context;

    public MyAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);


        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ListItem listItem = listItems.get(position);
        holder.textViewHead.setText(listItem.getHead());
        holder.textViewDesc.setText(listItem.getDesc());
        holder.textViewDate.setText(listItem.getDate());

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(c.getTime());

        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String inputString1 = holder.textViewDate.getText().toString();
        String inputString2 = formattedDate;


        try {
            Date date1 = myFormat.parse(inputString1);
            Date date2 = myFormat.parse(inputString2);
            long diff = date2.getTime() - date1.getTime();

            long days = diff / (24 * 60 * 60 * 1000);
            long hours = (diff / (60 * 60 * 1000)) - (days * 24);
            long minutes = (diff / (60 * 1000)) - (days * 24 * 60) - (hours * 60);
            //long seconds = (diff / (1000)) - (days * 24 * 60 * 60) - (hours * 60 * 60) - (minutes * 60);

            if (days == 0 && hours == 0) {
                holder.textViewDate.setText("| منذ " + minutes + " دقيقة ");
            } else if (days == 0) {
                holder.textViewDate.setText("| منذ " + hours + " ساعة," + minutes + " دقيقة");
            } else {
                holder.textViewDate.setText("| منذ " + days + " يوم," + hours + " ساعة," + minutes + "دقيقة");
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.textViewHead.setTextColor(Color.parseColor("#00aaff"));

        Picasso.with(context)
                .load(listItem.getImageUrl())
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewHead, textViewDesc, textViewDate;
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewHead = (TextView) itemView.findViewById(R.id.textViewHead);
            textViewDesc = (TextView) itemView.findViewById(R.id.textViewDesc);
            textViewDate = (TextView) itemView.findViewById(R.id.textViewDate);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);

        }
    }
}
