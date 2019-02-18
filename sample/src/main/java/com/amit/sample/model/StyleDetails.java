package com.amit.sample.model;

import java.util.Date;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * Created by Amit Jangid on 24,May,2018
**/
@Entity(tableName = "styleDetails")
public class StyleDetails
{
    @PrimaryKey(autoGenerate = true)
    private int code;
    private int active;
    private int styleCode;
    private int styleSyncStatus;

    private String styleNo;
    private String styImgFY;
    private String styImgHY;
    private String styImgFW;
    private String styImgHW;

    @ColumnInfo(name = "created_on")
    private Date createdOn;

    @ColumnInfo(name = "updated_on")
    private Date updatedOn;

    @Ignore
    public StyleDetails(int active, int styleCode, int styleSyncStatus,
                        String styleNo, String styImgFY, String styImgHY,
                        String styImgFW, String styImgHW,
                        Date createdOn, Date updatedOn)
    {
        this.active = active;
        this.styleCode = styleCode;
        this.styleSyncStatus = styleSyncStatus;

        this.styleNo = styleNo;
        this.styImgFY = styImgFY;
        this.styImgHY = styImgHY;
        this.styImgFW = styImgFW;
        this.styImgHW = styImgHW;

        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }

    public StyleDetails(int code, int active, int styleCode, int styleSyncStatus,
                        String styleNo, String styImgFY, String styImgHY,
                        String styImgFW, String styImgHW, Date createdOn, Date updatedOn)
    {
        this.code = code;
        this.active = active;
        this.styleCode = styleCode;
        this.styleSyncStatus = styleSyncStatus;

        this.styleNo = styleNo;
        this.styImgFY = styImgFY;
        this.styImgHY = styImgHY;
        this.styImgFW = styImgFW;
        this.styImgHW = styImgHW;

        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public int getActive()
    {
        return active;
    }

    public void setActive(int active)
    {
        this.active = active;
    }

    public int getStyleCode()
    {
        return styleCode;
    }

    public void setStyleCode(int styleCode)
    {
        this.styleCode = styleCode;
    }

    public int getStyleSyncStatus()
    {
        return styleSyncStatus;
    }

    public void setStyleSyncStatus(int styleSyncStatus)
    {
        this.styleSyncStatus = styleSyncStatus;
    }

    public String getStyleNo()
    {
        return styleNo;
    }

    public void setStyleNo(String styleNo)
    {
        this.styleNo = styleNo;
    }

    public String getStyImgFY()
    {
        return styImgFY;
    }

    public void setStyImgFY(String styImgFY)
    {
        this.styImgFY = styImgFY;
    }

    public String getStyImgHY()
    {
        return styImgHY;
    }

    public void setStyImgHY(String styImgHY)
    {
        this.styImgHY = styImgHY;
    }

    public String getStyImgFW()
    {
        return styImgFW;
    }

    public void setStyImgFW(String styImgFW)
    {
        this.styImgFW = styImgFW;
    }

    public String getStyImgHW()
    {
        return styImgHW;
    }

    public void setStyImgHW(String styImgHW)
    {
        this.styImgHW = styImgHW;
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
