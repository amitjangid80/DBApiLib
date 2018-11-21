package com.amit.sample.model;

/**
 * Created by AMIT JANGID on 21-Nov-18.
**/
public class LibraryTestModel
{
    private int code;
    private float value;
    private boolean locked;
    private String description;

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(String locked)
    {
        this.locked = locked.equalsIgnoreCase("true") || locked.equalsIgnoreCase("1");
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
