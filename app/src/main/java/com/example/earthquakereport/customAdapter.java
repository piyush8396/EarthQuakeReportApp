package com.example.earthquakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class customAdapter extends ArrayAdapter<EarthQuake> {
public customAdapter(Context context, ArrayList<EarthQuake> earthQuakes){
    super(context,0,earthQuakes);}

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null)
        {
            convertView=LayoutInflater.from(getContext()).inflate(R.layout.attribute_list,parent,false);
        }
        EarthQuake earthQuake=getItem(position);
        TextView magnitude=(TextView)convertView.findViewById(R.id.mag);
        magnitude.setText(String.valueOf(earthQuake.getMagnitude()));
        GradientDrawable circle=(GradientDrawable)magnitude.getBackground();
        int magnitudeColor=getMagnitudecolor(earthQuake.getMagnitude());
        circle.setColor(magnitudeColor);
        String placeArray[];
        placeArray=earthQuake.getPlace().split("of");
         if(placeArray.length==2){
        TextView place1=(TextView)convertView.findViewById(R.id.place1);
        place1.setText(placeArray[0]+"of");
        TextView place2=(TextView)convertView.findViewById(R.id.place2);
        place2.setText(placeArray[1]);}
         else{
             TextView place1=(TextView)convertView.findViewById(R.id.place1);
             place1.setText("Near the");
             TextView place2=(TextView)convertView.findViewById(R.id.place2);
             place2.setText(placeArray[0]);}


        Date dateobj=new Date(earthQuake.getDate());
        TextView date=(TextView)convertView.findViewById(R.id.date);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MMM dd,yyyy");
        date.setText(simpleDateFormat.format(dateobj));
        TextView time=(TextView)convertView.findViewById(R.id.time);
        SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("HH:mm a");
        time.setText(simpleDateFormat1.format(dateobj));



        return convertView;
    }
    private int getMagnitudecolor(double c)
    {  int id;
       int color=(int)c;

        switch (color)
        {
            case 1:
                id=R.color.magnitude1;
                break;
            case 2:
                id=R.color.magnitude2;
                break;
            case 3:
                id=R.color.magnitude3;
                break;
            case 4:
                id=R.color.magnitude4;
                break;
            case 5:
                id=R.color.magnitude5;
                break;
            case 6:
                id=R.color.magnitude6;
                break;
            case 7:
                id=R.color.magnitude7;
                break;
            case 8:
                id=R.color.magnitude9;
                break;
            case 9:
                id=R.color.magnitude9;
                break;
            default:
                id=R.color.magnitude10plus;
                break;

        }
        return ContextCompat.getColor(getContext(),id);
    }
}
