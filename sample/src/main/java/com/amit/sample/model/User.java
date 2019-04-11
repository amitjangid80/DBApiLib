package com.amit.sample.model;

/**
 * Created by AMIT JANGID on 10/04/2019.
**/
public class User
{
    private int _id;
    private int age;
    private float height;
    
    private String mobileNo;
    private String lastName;
    private String firstName;
    
    public int get_id()
    {
        return _id;
    }
    
    public void set_id(int _id)
    {
        this._id = _id;
    }
    
    public int getAge()
    {
        return age;
    }
    
    public void setAge(int age)
    {
        this.age = age;
    }
    
    public float getHeight()
    {
        return height;
    }
    
    public void setHeight(float height)
    {
        this.height = height;
    }
    
    public String getMobileNo()
    {
        return mobileNo;
    }
    
    public void setMobileNo(String mobileNo)
    {
        this.mobileNo = mobileNo;
    }
    
    public String getLastName()
    {
        return lastName;
    }
    
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    
    public String getFirstName()
    {
        return firstName;
    }
    
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
}
