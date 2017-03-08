package com.example.dhruv.uberyelp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

import static com.example.dhruv.uberyelp.R.id.list;

public class BusinessActivity extends AppCompatActivity {
    private String hardCodedLocation="Richardson";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);

        String searchterm=getIntent().getStringExtra("jsonstring");
        int businessID=getIntent().getIntExtra("businessID",0);

        Gson gson = new Gson();
        BusinessSearch result = gson.fromJson(searchterm, BusinessSearch.class);

        ImageView iv=(ImageView) findViewById(R.id.businessimg);
        Picasso.with(getBaseContext()).load(result.businesses[businessID].image_url).into(iv);

        TextView nm=(TextView) findViewById(R.id.name);
        nm.setText(result.businesses[businessID].name);

        TextView nm_ratings=(TextView) findViewById(R.id.numratings);
        nm_ratings.setText(result.businesses[businessID].review_count+" reviews");

        String address="";
        for(String ad:result.businesses[businessID].location.display_address)
            address+=ad+" ";

        TextView location=(TextView) findViewById(R.id.Location);
        location.setText(address);
        new BusinessActivity.DownloadSearchResults().execute(result.businesses[businessID]);
    }
    class Estimates implements Serializable{
        ArrayList<String> estimates=new ArrayList<String>();
        ArrayList<String> types=new ArrayList<String>();
        ArrayList<String> location=new ArrayList<String>();
    }
    private class DownloadSearchResults extends AsyncTask<Business , Void, Estimates> {
        @Override
        protected Estimates doInBackground(Business... params) {

            Business bus=params[0];
            //Uber Estimates

            String responseEstimate = YelpCall.findEstimate(32.9858,bus.coordinates.latitude , -96.7501, bus.coordinates.longitude,SendRequest.UBER_API);
            Gson gsonEstimate = new Gson();
            Pricess uber = gsonEstimate.fromJson(responseEstimate, Pricess.class);

            //Lyft Estimates
//            responseEstimate = YelpCall.findEstimate(32.9858,bus.coordinates.latitude , -96.7501, bus.coordinates.longitude,SendRequest.LYFT_API);
//            Cost_estimatess lyft = gsonEstimate.fromJson(responseEstimate, Cost_estimatess.class);
            Estimates e=new Estimates();

            String[] estimates=new String[uber.getPrices().length];
            String[] types=new String[uber.getPrices().length];
            int cnt=0;
            for(;cnt<uber.getPrices().length;cnt++){

                    e.estimates.add("" + uber.getPrices()[cnt].getEstimate());
                    e.types.add("" + uber.getPrices()[cnt].getDisplay_name());
                }
                e.location.add(bus.coordinates.longitude+"");
                e.location.add(bus.coordinates.latitude+"");

            //c.getEstimated_cost_cents_min() / 100 + "-" + c.getEstimated_cost_cents_max() / 100
//            for(Cost_estimates c:lyft.getCost_estimates()){
//                e.estimates.add( "abc");
//                e.types.add("xyz");
//            }
//            e.estimates.add(lyft.getCost_estimates().length+"");
//            e.types.add(lyft.getCost_estimates().length+"");

            return e;
        }


        protected void onPostExecute(final Estimates response) {


            CustomListRides adapter = new
                    CustomListRides(BusinessActivity.this, response.estimates.toArray(new String[0]), response.types.toArray(new String[0]));
            ListView list=(ListView)findViewById(R.id.ridesList);
            list.setAdapter(adapter);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id){
                    try {
                        PackageManager pm = getBaseContext().getPackageManager();
                        pm.getPackageInfo("com.ubercab", PackageManager.GET_ACTIVITIES);
                        String uri =
                                "uber://?action=setPickup&pickup[longitude]=-96.7501&pickup[latitude]=32.9858&dropoff[latitude]="+response.location.get(1)+"&dropoff[longitude]="+response.location.get(0)+"&client_id=aKNh29JTnwnaokqbxZlPOtL3Iz4f3O6T";
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(uri));
                        startActivity(intent);
                    } catch (PackageManager.NameNotFoundException e) {
                        // No Uber app! Open mobile website.
                        String url = "https://m.uber.com/sign-up?client_id=aKNh29JTnwnaokqbxZlPOtL3Iz4f3O6T";
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    }
                }
            });
        }
    }
}