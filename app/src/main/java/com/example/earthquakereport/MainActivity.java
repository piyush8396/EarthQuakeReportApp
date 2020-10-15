package com.example.earthquakereport;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<EarthQuake>> {
     private String ussg_data="https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=5&limit=10";

    private customAdapter c ;
    private TextView emptyTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(MainActivity.class.getSimpleName(),"in th oncreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emptyTextView=(TextView)findViewById(R.id.empty_view);
        ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        ListView earthquakeListView = (ListView) findViewById(R.id.list);
            earthquakeListView.setEmptyView(emptyTextView);
        c=new customAdapter(this,new ArrayList<EarthQuake>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(c);

        Log.v(MainActivity.class.getSimpleName(),"in th initLoader");
        if(isConnected)
       getSupportLoaderManager().initLoader(0,null,  this).forceLoad();
        else{
            emptyTextView.setText("No Internet Connection");
            ProgressBar progressBar=(ProgressBar)findViewById(R.id.loading_spinner);
            progressBar.setVisibility(View.INVISIBLE);}


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

    @NonNull
    @Override
    public Loader<ArrayList<EarthQuake>> onCreateLoader(int id, @Nullable Bundle args) {
        Log.v(MainActivity.class.getSimpleName(),"in the OnCreateLoader");
        return new EarthQuakeLoader(MainActivity.this,ussg_data);

    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<EarthQuake>> loader, ArrayList<EarthQuake> data) {
        emptyTextView.setText("EarthQuake data is not found");
        c.clear();
        Log.v(MainActivity.class.getSimpleName(),"in the OnLoadFinished");
          if(data!=null&&!data.isEmpty()){
     c.addAll(data);
                }
        ProgressBar progressBar=(ProgressBar)findViewById(R.id.loading_spinner);
          progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<EarthQuake>> loader) {
       // c=new customAdapter(this,new ArrayList<EarthQuake>());
        Log.v(MainActivity.class.getSimpleName(),"in the OnLoaderReset");
        c.clear();

    }


//    private class EarthQuakeAsync extends AsyncTask<String,Void,ArrayList<EarthQuake>>
//    {
//
//        @Override
//        protected ArrayList<EarthQuake> doInBackground(String... strings) {
//            if(strings.length<1||strings[0]==null)
//                return null;
//            ArrayList<EarthQuake> earthquakes = queryclass.extractEarthquakes(strings[0]);
//            return earthquakes;
//        }
//
//        @Override
//        protected void onPostExecute(ArrayList<EarthQuake> earthquakes) {
//            c.clear();
//           if(earthquakes!=null&&!earthquakes.isEmpty())
//           {
//               c.addAll(earthquakes);
//           }
//        }
//    }
}