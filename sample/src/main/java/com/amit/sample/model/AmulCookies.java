package com.amit.sample.model;

/**
 * Created by Amit Jangid on 24,May,2018
 **/
public class AmulCookies
{
    private String loginStatus;

    public AmulCookies(String loginStatus)
    {
        this.loginStatus = loginStatus;
    }

    public String getLoginStatus()
    {
        return loginStatus;
    }

    public void setLoginStatus(String loginStatus)
    {
        this.loginStatus = loginStatus;
    }
}
