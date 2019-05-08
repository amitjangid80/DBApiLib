package com.amit.sample.db;

import java.util.Date;

import androidx.room.TypeConverter;

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
