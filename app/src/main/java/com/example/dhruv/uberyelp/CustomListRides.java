package com.example.dhruv.uberyelp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by dhruv on 3/5/17.
 */

public class CustomListRides extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] estimates;
    private final String[] types;

    public CustomListRides(Activity context,
                                   String[] estimates, String[] types) {
        super(context, R.layout.rides_single, estimates);
        this.context = context;
        this.estimates=estimates;
        this.types=types;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.rides_single, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.type);
        TextView txtSummary = (TextView) rowView.findViewById(R.id.estimate);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
       txtSummary.setText(estimates[position]);
        if(types[position].toLowerCase().contains("uber")){
            imageView.setImageResource(R.drawable.uber);
        }
        else{
            imageView.setImageResource(R.drawable.lyft);
        }
        txtTitle.setText(types[position]);
        return rowView;
    }
}