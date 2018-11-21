package com.amit.sample.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.util.Log;

import com.amit.sample.model.ImageDetails;
import com.amit.sample.model.StyleDetails;

/**
 * Created by Amit Jangid on 24,May,2018
**/
@Database(entities =
        {
                StyleDetails.class,
                ImageDetails.class
        },
        version = 1,
        exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase
{
    private static final String TAG = AppDatabase.class.getSimpleName();
    private static final String DATABASE_NAME = "tryARing";
    private static final Object LOCK = new Object();
    private static AppDatabase sInstance;

    public static AppDatabase getInstance(Context context)
    {
        if (sInstance == null)
        {
            synchronized (LOCK)
            {
                Log.e(TAG, "getInstance: Creating new instance.");

                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, AppDatabase.DATABASE_NAME)
                        .build();
            }
        }

        Log.e(TAG, "getInstance: Getting database instance.");
        return sInstance;
    }

    public abstract StyleDetailsDao styleDetailsDao();
    public abstract ImageDetailsDao imageDetailsDao();
}
