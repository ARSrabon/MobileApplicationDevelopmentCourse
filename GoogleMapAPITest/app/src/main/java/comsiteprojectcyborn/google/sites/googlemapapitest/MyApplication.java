package comsiteprojectcyborn.google.sites.googlemapapitest;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by msrabon on 12/26/16.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }

}