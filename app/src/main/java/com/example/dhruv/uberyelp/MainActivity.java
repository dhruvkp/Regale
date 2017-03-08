package com.example.dhruv.uberyelp;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    GoogleApiClient mGoogleApiClient=null;
    private Location mLastLocation;
    private String mLatitudeText;
    private String mLongitudeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView list;


        // Create an instance of GoogleAPIClient.
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        
        
        // Defined Array values to show in ListView
        final String[] web = new String[] {
                "Asian",
                "Breakfast",
                "Pizza",
                "Mediterranean",
                "Italian",
                "Indian",
                "Sandwiches",
                "Japanese",
                "Thai",
                "Vegetarian",
                "Vegan",
                "Subs",
                "Seafood"
        };
        Integer[] imageId = {
                R.drawable.asian,
                R.drawable.breakfast,
                R.drawable.pizza,
                R.drawable.medittaranian,
                R.drawable.italian,
                R.drawable.sandwich,
                R.drawable.japanese,
                R.drawable.thai,
                R.drawable.vegetarian,
                R.drawable.vegan,
                R.drawable.subs,
                R.drawable.seafood

        };
        CustomList adapter = new
                CustomList(MainActivity.this, web, imageId);
        list=(ListView)findViewById(R.id.list);
        TextView mEditText3 = (TextView) findViewById(R.id.searchtext);
        mEditText3.setImeActionLabel("Search", KeyEvent.KEYCODE_ENTER);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                android.content.Intent myIntent = new android.content.Intent(getBaseContext(), SearchResults.class);
                myIntent.putExtra("searchterm",parent.getItemAtPosition(position).toString());
                startActivity(myIntent);
            }
        });

    }
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        /*Context context = MyApplication.getAppContext();
        int localRequest=0;
        if (ContextCompat.checkSelfPermission( context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    localRequest);
        }
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                    mGoogleApiClient);

        if (mLastLocation != null) {
            mLatitudeText=String.valueOf(mLastLocation.getLatitude());
            mLongitudeText=String.valueOf(mLastLocation.getLongitude());
        }*/
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
