package com.example.angelhack;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class DisplayPlacesActivity extends AppCompatActivity implements PlacesAdapter.ViewHolder.ClickListener{


    private RecyclerView recyclerView;
    private PlacesAdapter placesAdapter;
    private ArrayList<PlaceModel> arrayList;
    private String source;
    private String destination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_places);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        source = getIntent().getStringExtra("source");
        destination = getIntent().getStringExtra("destination");

        arrayList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        placesAdapter = new PlacesAdapter(this, arrayList,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(placesAdapter);

        new FetchData().execute();

    }

    @Override
    public void onItemClicked(int position) {

    }

    @Override
    public boolean onItemLongClicked(int position) {
        return false;
    }

    private class FetchData extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            Boolean success = false;
            source = source.replace(' ','+');
            destination = destination.replace(' ','+');
            StringBuilder data = new StringBuilder("");
            try {
                Log.d("DEBUG", "inside try");
                URL url = new URL("https://maps.googleapis.com/maps/api/directions/json?origin="+
                        source+"&destination="+destination+"&key="+BuildConfig.MAPS_API);
                Log.d("URL",url.toString());
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    data.append(line);
                    Log.d("DEBUG", line);
                }

                if (inputStream != null) {
                    inputStream.close();
                    parseResult(data.toString());
                    Log.d("DEBUG", "input stream not null");
                    success = true;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return success;
        }
    }

    private void parseResult(final String data) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject response = new JSONObject(data);
                    JSONArray data2 = response.getJSONArray("routes");
                    JSONObject data3 = data2.getJSONObject(0);
                    JSONArray data4 = data3.getJSONArray("legs");
                    JSONObject data5 = data4.getJSONObject(0);
                    JSONArray data6 = data5.getJSONArray("steps");

                    for (int i=0;i<data6.length();i++) {
                        JSONObject jsonObject = data6.getJSONObject(i);
                        PlaceModel model = new PlaceModel();
                        model.setPlace(jsonObject.getString("html_instructions"));
                        model.setLat(jsonObject.getJSONObject("start_location").getString("lat"));
                        model.setLon(jsonObject.getJSONObject("start_location").getString("lng"));
                        arrayList.add(model);
                        Log.d("haha",model.getPlace());
                    }
                    placesAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });

    }
}
