package com.example.weatherapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class MeteoListModel extends ArrayAdapter<MeteoItem> {

    private List<MeteoItem> listitems;
    private int resource;
    private static Map<String,Integer> imgs=new HashMap<>();
    static {
        imgs.put("Clear",R.drawable.clear);
        imgs.put("Clouds",R.drawable.cloud);
        imgs.put("Rain",R.drawable.rain);
    }

    MeteoListModel(@NonNull Context context,int resource,List<MeteoItem> data){
        super(context,resource,data);
        this.listitems=data;
        this.resource=resource;
    }


    @Override
    public View getView(int position, @Nullable View convertview, @NonNull ViewGroup parent){
        View listItem=convertview;
        if (listItem==null)
            listItem= LayoutInflater.from(getContext()).inflate(resource,parent,false);
        ImageView imageview=listItem.findViewById(R.id.WeatherImage);
        TextView maxtmp=listItem.findViewById(R.id.TMaxValue);
        TextView mintmp=listItem.findViewById(R.id.TMinValue);
        TextView pression=listItem.findViewById(R.id.PValue);
        TextView humidity=listItem.findViewById(R.id.HValue);
        TextView date=listItem.findViewById(R.id.DateText);
        String imagename=listitems.get(position).getImage();
        if (imagename!=null)
            imageview.setImageResource(imgs.get(imagename));
        maxtmp.setText(String.valueOf(listitems.get(position).getMaxtemp()));
        mintmp.setText(String.valueOf(listitems.get(position).getMintemp()));
        pression.setText(String.valueOf(listitems.get(position).getPression()));
        humidity.setText(String.valueOf(listitems.get(position).getHumidity()));
        date.setText(String.valueOf(listitems.get(position).getDate()));

        return listItem;
    }
}
