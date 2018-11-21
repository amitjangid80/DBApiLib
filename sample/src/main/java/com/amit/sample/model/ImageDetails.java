package com.amit.sample.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/**
 * Created by Amit Jangid on 24,May,2018
**/
/*@Entity(tableName = "imgDetails",
        foreignKeys = @ForeignKey(
                entity = StyleDetails.class,
                parentColumns = "imgCode",
                childColumns = "styleCode"))*/
@Entity(tableName = "imgDetails")
public class ImageDetails
{
    @PrimaryKey(autoGenerate = true)
    private int imgCode;

    private int styleCode;
    private int imgSyncStatus;

    private String imgPath;

    @ColumnInfo(name = "created_on")
    private Date createdOn;

    @ColumnInfo(name = "updated_on")
    private Date updatedOn;

    @Ignore
    public ImageDetails(int styleCode, int imgSyncStatus, String imgPath, Date createdOn, Date updatedOn)
    {
        this.styleCode = styleCode;
        this.imgSyncStatus = imgSyncStatus;
        this.imgPath = imgPath;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }

    public ImageDetails(int imgCode, int styleCode, int imgSyncStatus, String imgPath, Date createdOn, Date updatedOn)
    {
        this.imgCode = imgCode;
        this.styleCode = styleCode;
        this.imgSyncStatus = imgSyncStatus;
        this.imgPath = imgPath;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }

    public int getImgCode()
    {
        return imgCode;
    }

    public void setImgCode(int imgCode)
    {
        this.imgCode = imgCode;
    }

    public int getStyleCode()
    {
        return styleCode;
    }

    public void setStyleCode(int styleCode)
    {
        this.styleCode = styleCode;
    }

    public int getImgSyncStatus()
    {
        return imgSyncStatus;
    }

    public void setImgSyncStatus(int imgSyncStatus)
    {
        this.imgSyncStatus = imgSyncStatus;
    }

    public String getImgPath()
    {
        return imgPath;
    }

    public void setImgPath(String imgPath)
    {
        this.imgPath = imgPath;
    }

    public Date getCreatedOn()
    {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn)
    {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn()
    {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn)
    {
        this.updatedOn = updatedOn;
    }
}
