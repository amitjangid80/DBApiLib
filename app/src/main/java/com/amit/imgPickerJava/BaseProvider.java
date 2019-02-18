package com.amit.imgPickerJava;

import android.content.ContextWrapper;

import androidx.annotation.NonNull;

/**
 * Created by AMIT JANGID on 18/02/2019.
**/
public abstract class BaseProvider extends ContextWrapper
{
    private final ImagePickerActivity activity;

    public BaseProvider(@NonNull ImagePickerActivity activity)
    {
        super(activity);
        this.activity = activity;
    }

    /**
     * Cancel operation and Set Error Message
     *
     * @param error Error Message
    **/
    protected final void setError(@NonNull String error)
    {
        this.onFailure();
        this.activity.setError(error);
    }

    /**
     * Cancel operation and Set Error Message
     *
     * @param errorRes Error Message
    **/
    protected final void setError(int errorRes)
    {
        String error = this.getString(errorRes);
        this.setError(error);
    }

    /**
     * Call this method when task is cancel in between the operation.
     * E.g. user hit back-press
    **/
    protected void setResultCancel()
    {
        onFailure();
        activity.setResultCancel();
    }

    protected void onFailure()
    {

    }
}
