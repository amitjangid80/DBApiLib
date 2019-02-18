package com.amit.sample;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.amit.img_picker.ImagePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

// import com.fxn.pix.Pix;
// import com.github.dhaval2404.imagepicker.ImagePicker;

public class ImagePickerActivity extends AppCompatActivity
{
    private final String TAG = ImagePickerActivity.class.getSimpleName();
    private static final int GALLERY_PICK_CODE = 101;

    private ImageView imgSelectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_picker);

        imgSelectedImage = findViewById(R.id.imgSelectedImage);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode)
        {
            case GALLERY_PICK_CODE:

                try
                {
                    if (resultCode == RESULT_OK && data != null && data.getData() != null)
                    {
                        ProgressDialog progressDialog = ProgressDialog.show(
                                this, null, "Please wait.....",
                                true, false);

                        /*ArrayList<String> returnValue = data.getStringArrayListExtra(Pix.IMAGE_RESULTS);

                        for (int i = 0; i < returnValue.size(); i++)
                        {
                            Log.e(TAG, "onActivityResult: clicked images is: " + returnValue.get(i));
                        }*/

                        Uri selectedImage = data.getData();
                        // imgSelectedImage.setImageURI(selectedImage);

                        GlideApp.with(this)
                                .load(selectedImage)
                                .fitCenter()
                                .into(imgSelectedImage);

                        progressDialog.dismiss();
                    }
                }
                catch (Exception e)
                {
                    Log.e(TAG, "onActivityResult: exception while setting image:\n");
                    e.printStackTrace();
                }

                break;
        }
    }

    public void pickImage(View view)
    {
        ImagePicker.Companion.with(this)
                .cameraOnly()
                .compress(100)
                .start(GALLERY_PICK_CODE);
    }

    public void setImageWithGlide(View view)
    {

    }
}
