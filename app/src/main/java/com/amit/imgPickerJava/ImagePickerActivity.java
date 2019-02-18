package com.amit.imgPickerJava;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import java.io.File;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

/**
 * Created by AMIT JANGID on 18/02/2019.
**/
public class ImagePickerActivity extends FragmentActivity
{
    private CropProvider mCropProvider;
    private CameraProvider mCameraProvider;
    private GalleryProvider mGalleryProvider;
    private CompressionProvider mCompressionProvider;

    private File mCropFile;
    private File mOriginalFile;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.loadBundle();
    }

    private void loadBundle()
    {
        // mCropProvider = new CropProvider(this);
        // mCompressionProvider = new CompressionProvider(this);

        Bundle bundle = getIntent().getExtras();
        // ImageProvider provider = bundle.getSerializable(ImagePicker.EXTRA_IMAGE_PROVIDER);
    }

    public final void setImage(@NonNull File file)
    {
        mOriginalFile = file;

        // if (mCropProvider)
    }

    public final void setResult(File file)
    {
        Intent intent = new Intent();
        intent.setData(Uri.fromFile(file));
        intent.putExtra(ImagePicker.EXTRA_FILE_PATH, file.getAbsolutePath());
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    public final void setResultCancel()
    {
        setResult(Activity.RESULT_CANCELED, new Intent());
        finish();
    }

    public final void setError(String message)
    {
        Intent intent = new Intent();
        intent.putExtra(ImagePicker.EXTRA_ERROR, message);
        setResult(ImagePicker.RESULT_ERROR, intent);
        finish();
    }
}
