package com.amit.sample.db;

import com.amit.sample.model.StyleDetails;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

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
