package com.example.dhruv.uberyelp;

/**
 * Created by dhruv on 3/4/17.
 */

/**
 * Created by Rucha on 3/4/2017.
 */
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class CustomListSearchResults extends ArrayAdapter<String>{

    private final Activity context;
    private final String[] web;
    private final String[] imageId;
    private final String[] summary_id;
    private final Double[] ratings;

    public CustomListSearchResults(Activity context,
                      String[] web, String[] imageId, Double[] ratings, String[] summary_id) {
        super(context, R.layout.list_single_search_results, web);
        this.context = context;
        this.web = web;
        this.imageId = imageId;
        this.ratings=ratings;
        this.summary_id = summary_id;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_single_search_results, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
        TextView txtSummary = (TextView) rowView.findViewById(R.id.list_entry_summary);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txtTitle.setText(web[position]);
        txtSummary.setText(summary_id[position]);
        Picasso.with(context).load(imageId[position]).into(imageView);
        RatingBar rb=(RatingBar) rowView.findViewById(R.id.rating);
        rb.setRating(ratings[position].floatValue());
        return rowView;
    }
}