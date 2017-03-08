package com.example.dhruv.uberyelp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ListView;
        import android.widget.Toast;

import com.google.gson.Gson;

public class SearchResults extends AppCompatActivity {
    ListView list;
    private String hardCodedLocation="Richardson";

    String[] titles;
    String[] images;
    Double[] ratings;
    String[] summary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        String searchterm=getIntent().getStringExtra("searchterm");
        new DownloadSearchResults().execute(searchterm,hardCodedLocation);
    }

    private class DownloadSearchResults extends AsyncTask<String , Integer, String> {
        @Override
        protected String doInBackground(String... params) {

            String response =YelpCall.findTerm(params[0],params[1]);

            return response;
        }

        protected void onProgressUpdate(Integer... progress) {
        }

        protected void onPostExecute(String response) {
            Gson gson = new Gson();
            BusinessSearch result = gson.fromJson(response, BusinessSearch.class);

            final String response1=response;
            titles=new String[result.businesses.length];
            images=new String[result.businesses.length];
            ratings=new Double[result.businesses.length];
            summary=new String[result.businesses.length];
            int cnt=0;
            for(Business bus : result.businesses){
                titles[cnt]=bus.name;
                images[cnt]=bus.image_url;
                ratings[cnt]=bus.rating;
                String address="";
                for(String ad:bus.location.display_address)
                    address+=ad+" ";
                summary[cnt++]=address;
            }

            CustomListSearchResults adapter = new
                    CustomListSearchResults(SearchResults.this, titles, images, ratings, summary);
            list=(ListView)findViewById(R.id.list);
            list.setAdapter(adapter);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id){
                    android.content.Intent myIntent = new android.content.Intent(getBaseContext(), BusinessActivity.class);
                    myIntent.putExtra("jsonstring",response1);
                    myIntent.putExtra("businessID",position);
                    startActivity(myIntent);                }
            });
        }
    }
}
