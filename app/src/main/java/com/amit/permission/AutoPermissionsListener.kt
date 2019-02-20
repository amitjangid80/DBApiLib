package com.amit.permission

/**
 * Created by AMIT JANGID on 20/02/2019.
**/
interface AutoPermissionsListener
{
    fun onGranted(requestCode: Int, permissions: Array<String>)
    fun onDenied(requestCode: Int, permissions: Array<String>)
}