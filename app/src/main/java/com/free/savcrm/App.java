package com.free.savcrm;

import android.app.Application;
import android.support.multidex.MultiDex;

/**
 * Created by bynkaa on 7/25/2016.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
    }
}
