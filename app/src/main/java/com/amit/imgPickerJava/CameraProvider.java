package com.amit.imgPickerJava;

import android.app.Activity;
import android.content.Intent;

import com.amit.dbapilibrary.R;

import java.io.File;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import static com.amit.imgPickerJava.PermissionUtil.isPermissionGranted;

/**
 * Created by AMIT JANGID on 18/02/2019.
**/
public class CameraProvider extends BaseProvider
{
    private static final String[] REQUIRED_PERMISSIONS = new String[]
            {"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE"};

    private static final int CAMERA_INTENT_REQ_CODE = 4281;
    private static final int PERMISSION_INTENT_REQ_CODE = 4282;

    private final ImagePickerActivity mActivity;

    /**
     * Temp Camera File
    **/
    private File mCameraFile = null;

    public CameraProvider(@NonNull ImagePickerActivity activity)
    {
        super(activity);
        mActivity = activity;
    }

    /**
     * Start Camera Capture Intent
    **/
    void startIntent()
    {
        checkPermission();
    }

    /**
     * Check Require permission for Taking Picture.
     *
     * If permission is not granted request Permission, Else start Camera Intent
    **/
    private void checkPermission()
    {
        if (!isPermissionGranted(mActivity, REQUIRED_PERMISSIONS))
        {
            ActivityCompat.requestPermissions(mActivity,
                    REQUIRED_PERMISSIONS, PERMISSION_INTENT_REQ_CODE);
        }
        else
        {
            this.startCameraIntent();
        }
    }

    /**
     * Start Camera Intent
     *
     * Create Temporary File object and Pass it to Camera Intent
    **/
    private void startCameraIntent()
    {
        // Create and get empty file to store capture image content
        mCameraFile = FileUtil.getCameraFile();

        // Check if file exists
        if (mCameraFile != null && mCameraFile.exists())
        {
            Intent cameraIntent = IntentUtil.getCameraIntent(this, mCameraFile);
            mActivity.startActivityForResult(cameraIntent, CAMERA_INTENT_REQ_CODE);
        }
        else
        {
            setError(R.string.error_failed_to_create_camera_image_file);
        }
    }

    /**
     * Handle Requested Permission Result
    **/
    public void onRequestPermissionsResult(int requestCode)
    {
        if (requestCode == PERMISSION_INTENT_REQ_CODE)
        {
            //Check again if permission is granted
            if (isPermissionGranted(mActivity, REQUIRED_PERMISSIONS))
            {
                //Permission is granted, Start Camera Intent
                startCameraIntent();
            }
            else
            {
                //Exit with error message
                setError(getString(R.string.permission_camera_denied));
            }
        }
    }

    /**
     * Handle Camera Intent Activity Result
     *
     * @param requestCode  It must be {@link CameraProvider#CAMERA_INTENT_REQ_CODE}
     * @param resultCode  For success it should be {@link Activity#RESULT_OK}
     * @param data Result Intent
    **/
    public final void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        if (requestCode == CAMERA_INTENT_REQ_CODE)
        {
            if (resultCode == Activity.RESULT_OK)
            {
                this.handleResult(data);
            }
            else
            {
                this.setResultCancel();
            }
        }
    }

    /**
     * This method will be called when final result fot this provider is enabled.
    **/
    private void handleResult(Intent data)
    {
        mActivity.setImage(mCameraFile);
    }
}
