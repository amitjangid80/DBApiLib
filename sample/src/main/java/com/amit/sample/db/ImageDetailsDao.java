package com.amit.sample.db;

import com.amit.sample.model.ImageDetails;

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
public interface ImageDetailsDao
{
    @Query("SELECT * FROM imgDetails ORDER BY imgCode")
    LiveData<List<ImageDetails>> loadAllImageDetails();

    @Insert
    void insertImageDetails(ImageDetails imageDetails);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updatedImageDetails(ImageDetails imageDetails);

    @Query("SELECT * FROM imgDetails WHERE imgCode = :imgCode")
    LiveData<List<ImageDetails>> loadImageDetailsByCode(int imgCode);
}
