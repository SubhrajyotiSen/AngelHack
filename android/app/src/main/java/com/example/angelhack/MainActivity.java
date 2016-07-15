package com.example.angelhack;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends FragmentActivity implements GoogleApiClient.OnConnectionFailedListener {


    EditText sourceEditText;
    EditText destinationEditText;
    GoogleApiClient googleApiClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(BuildConfig.TWITTER_KEY, BuildConfig.TWITTER_SECRET);
        Fabric.with(this, new Crashlytics(), new Twitter(authConfig));
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        googleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, MainActivity.this)
                .build();

        sourceEditText = (EditText) findViewById(R.id.sourceEditText);
        destinationEditText = (EditText) findViewById(R.id.destinationEditText);

        sourceEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
                try {
                    Intent intent =
                            new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                                    .build(MainActivity.this);
                    startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
                } catch (GooglePlayServicesRepairableException e) {
                    // TODO: Handle the error.
                } catch (GooglePlayServicesNotAvailableException e) {
                    // TODO: Handle the error.
                }
            }
        });

        destinationEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int PLACE_AUTOCOMPLETE_REQUEST_CODE = 2;
                try {
                    Intent intent =
                            new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                                    .build(MainActivity.this);
                    startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
                } catch (GooglePlayServicesRepairableException e) {
                    // TODO: Handle the error.
                } catch (GooglePlayServicesNotAvailableException e) {
                    // TODO: Handle the error.
                }
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,DisplayPlacesActivity.class);
                intent.putExtra("source",sourceEditText.getText().toString());
                intent.putExtra("destination",destinationEditText.getText().toString());
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Twitter.logOut();
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1: if (resultCode == RESULT_OK) {
                        Place place = PlaceAutocomplete.getPlace(this, data);
                        Log.i("LOL", "Place: " + place.getName());
                        sourceEditText.setText(place.getAddress());
                    } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                        Status status = PlaceAutocomplete.getStatus(this, data);
                        // TODO: Handle the error.
                        Log.i("LOL", status.getStatusMessage());
                    }
                break;
            case  2: if (resultCode == RESULT_OK) {
                        Place place = PlaceAutocomplete.getPlace(this, data);
                        Log.i("LOL", "Place: " + place.getName());
                        destinationEditText.setText(place.getAddress());
                    } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                        Status status = PlaceAutocomplete.getStatus(this, data);
                        // TODO: Handle the error.
                        Log.i("LOL", status.getStatusMessage());
                    }
                break;
        }

    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {


    }
}
