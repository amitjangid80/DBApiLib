package com.amit.sample.model;

import java.sql.Blob;

/**
 * Created by AMIT JANGID on 08/01/2019.
**/
public class Image
{
    private int code;
    private byte[] image;

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public byte[] getImage()
    {
        return image;
    }

    public void setImage(byte[] image)
    {
        this.image = image;
    }
}
