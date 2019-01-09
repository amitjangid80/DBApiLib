package com.amit.sample;

import android.app.Application;

import com.amit.utilities.SharedPreferenceData;

/**
 * Created by AMIT JANGID on 08/01/2019.
**/
public class MyApplication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();

        SharedPreferenceData sharedPreferenceData = new SharedPreferenceData(this);
        sharedPreferenceData.setValue("dbName", "USER_DB.db");
    }
}
