package com.geekband.tingyou;

import android.content.Context;
import android.content.res.Configuration;

/**
 *
 */
public class ApplicationContext extends android.app.Application{

    /**
     * in 3G status, show image or not.
     */
    public static boolean showImage = true;

    private static ApplicationContext context; // to get Context outside Context object

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        //init umeng, volly, mobclick etc.
    }

    public static ApplicationContext getContext() {
        return context;
    }

    @Override
    public void onTerminate() {

        // release resourse
        super.onTerminate();
    }

    @Override
    public final void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
    }
}
