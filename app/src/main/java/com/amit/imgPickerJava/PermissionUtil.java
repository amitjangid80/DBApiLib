package com.amit.imgPickerJava;

import android.content.Context;
import android.content.pm.PackageManager;

import java.util.ArrayList;

import androidx.core.content.ContextCompat;

/**
 * Created by AMIT JANGID on 18/02/2019.
**/
public class PermissionUtil
{
    /**
     * Check if Camera Permission is granted
     *
     * @return true if specified permission is granted
    **/
    private static boolean hasPermission(Context context, String permission)
    {
        int selfPermission = ContextCompat.checkSelfPermission(context, permission);
        return selfPermission == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Check if Specified Permissions are granted or not. If single permission is denied then
     * function will return false.
     *
     * @param context Application Context
     * @param permissions Array of Permission to Check
     *
     * @return true if all specified permission is granted
    **/
    static boolean isPermissionGranted(Context context, String[] permissions)
    {
        ArrayList<String> tempList = new ArrayList<>();

        for (int i = 0; i < permissions.length; i++)
        {
            String permission = permissions[i];

            if (hasPermission(context, permission))
            {
                tempList.add(permission);
            }
        }

        return tempList.size() == permissions.length;
    }
}
