package com.example.earthquakereport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
     private String ussg_data="https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=5&limit=10";
    private customAdapter c ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        c=new customAdapter(this,new ArrayList<EarthQuake>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(c);
         EarthQuakeAsync earthQuakeAsync=new EarthQuakeAsync();
          earthQuakeAsync.execute(ussg_data);

        // Find a reference to the {@link ListView} in the layout

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               // Toast.makeText(MainActivity.this,"item is clicked",Toast.LENGTH_SHORT).show();

                EarthQuake e=c.getItem(i);
                  String url=e.getUrl();
                Intent k = new Intent(Intent.ACTION_VIEW);
                k.setData(Uri.parse(url));
                startActivity(k);

            }
        });
    }
    private class EarthQuakeAsync extends AsyncTask<String,Void,ArrayList<EarthQuake>>
    {

        @Override
        protected ArrayList<EarthQuake> doInBackground(String... strings) {
            if(strings.length<1||strings[0]==null)
                return null;
            ArrayList<EarthQuake> earthquakes = queryclass.extractEarthquakes(strings[0]);
            return earthquakes;
        }

        @Override
        protected void onPostExecute(ArrayList<EarthQuake> earthquakes) {
            c.clear();
           if(earthquakes!=null&&!earthquakes.isEmpty())
           {
               c.addAll(earthquakes);
           }
        }
    }
}