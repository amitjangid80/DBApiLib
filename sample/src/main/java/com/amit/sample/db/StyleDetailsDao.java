package com.amit.sample.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.amit.sample.model.StyleDetails;

import java.util.List;

/**
 * Created by Amit Jangid on 24,May,2018
 **/
@Dao
public interface StyleDetailsDao
{
    @Query("SELECT * FROM styleDetails ORDER BY code ASC")
    LiveData<List<StyleDetails>> getAllStyleDetails();

    /*@Query("SELECT MAX(code) FROM styleDetails")
    LiveData<List<StyleDetails>> getMaxStyleDetails();*/

    @Insert
    void insertStyleDetail(StyleDetails styleDetails);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateStyleDetail(StyleDetails styleDetails);

    @Query("SELECT * FROM styleDetails WHERE code = :code")
    LiveData<List<StyleDetails>> getStyleDetailsByCode(int code);
}
