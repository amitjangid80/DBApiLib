package com.amit.sample.db;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by Amit Jangid on 24,May,2018
 **/
public class DateConverter
{
    @TypeConverter
    public static Date toDate(Long timeStamp)
    {
        return timeStamp == null ? null : new Date(timeStamp);
    }

    @TypeConverter
    public static Long toTimeStamp(Date date)
    {
        return date == null ? null : date.getTime();
    }
}
