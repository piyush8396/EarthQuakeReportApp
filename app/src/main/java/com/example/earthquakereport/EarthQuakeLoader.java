package com.example.earthquakereport;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.ArrayList;
import java.util.List;

public class EarthQuakeLoader extends AsyncTaskLoader<ArrayList<EarthQuake>> {
   private String url;
    public EarthQuakeLoader(@NonNull Context context,String url) {
        super(context);
        this.url=url;
    }

    @Override
    protected void onStartLoading() {
        Log.v(MainActivity.class.getSimpleName(),"in the OnStartLoading");
        forceLoad();

    }

    @Nullable
    @Override
    public ArrayList<EarthQuake> loadInBackground() {
        Log.v(MainActivity.class.getSimpleName(),"in the LoadInBackGround");
        if(url==null||url.length()<1) {
            Log.v(MainActivity.class.getSimpleName(),"Url is null");
            return null;
        }
        ArrayList<EarthQuake> earthQuake=queryclass.extractEarthquakes(url);
        return earthQuake;
    }
}
