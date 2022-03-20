package com.example.summer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder>{

    ArrayList<RvModel> RvModel_list;
    Context context;

    public RvAdapter(ArrayList<RvModel> rvModel_list, Context context) {
        RvModel_list = rvModel_list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RvAdapter.ViewHolder holder, int position) {

        holder.windspeed_item.setText(RvModel_list.get(position).getWindSpeed()+"Km/h");
        holder.temperature_item.setText(RvModel_list.get(position).getTemperature()+"°C");
        Picasso.get().load("https:"+RvModel_list.get(position).getIcon()).into(holder.Icon_item);

        //For time we have to convert into the format we want
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        SimpleDateFormat output = new SimpleDateFormat("hh:mm aa");

        try {
            //API Gives us Date in String format and we dont want that format
            //hence we Parse the date in string to Date DataType and..
            //Then Use the output date format and format() to extract the date format we want from the
            //parsed date..
            // format() returns a String which we set to time_item

            Date t = input.parse(RvModel_list.get(position).getTime());
            holder.time_item.setText(output.format(t));

        } catch (ParseException e) {
            e.printStackTrace();
        }



    }

    @Override
    public int getItemCount() {
        return RvModel_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView time_item,temperature_item,windspeed_item;
        ImageView Icon_item;

        public ViewHolder(View itemView) {
            super(itemView);

            time_item = itemView.findViewById(R.id.time_item);
            temperature_item = itemView.findViewById(R.id.temperature_item);
            windspeed_item = itemView.findViewById(R.id.windspeed_item);
            Icon_item = itemView.findViewById(R.id.Icon_item);

        }
    }
}
