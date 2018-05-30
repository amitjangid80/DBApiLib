package com.amit.sample.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.amit.sample.model.ImageDetails;

import java.util.List;

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
