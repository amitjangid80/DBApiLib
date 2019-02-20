package com.amit.sample;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.amit.img_picker.ImagePicker;
import com.amit.permission.AutoPermissions;
import com.amit.permission.AutoPermissionsListener;
import com.amit.permission.PermissionHelper;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

// import com.fxn.pix.Pix;
// import com.github.dhaval2404.imagepicker.ImagePicker;

public class ImagePickerActivity extends AppCompatActivity implements AutoPermissionsListener {
    private static final int GALLERY_PICK_CODE = 101;
    private final String TAG = ImagePickerActivity.class.getSimpleName();
    private ImageView imgSelectedImage;

    // private PermissionHelper permissionHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_picker);

        imgSelectedImage = findViewById(R.id.imgSelectedImage);

        /*permissionHelper = new PermissionHelper.Builder()
                .with(this)
                .requestCode(GALLERY_PICK_CODE)
                .setPermissionResultCallback(this)
                .askFor(Permission.CALENDAR, Permission.CONTACTS, Permission.CAMERA, Permission.STORAGE)
                .rationalMessage("Permissions are required for app to work properly")
                .build();

        permissionHelper.requestPermissions();*/

        String[] requestPermissionsList = new String[]{
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_CONTACTS,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.READ_CALENDAR,
                Manifest.permission.WRITE_CALENDAR};

        // AutoPermissions.Companion.loadAllPermissions(this, GALLERY_PICK_CODE);
        // AutoPermissions.Companion.loadSelectedPermission(this, GALLERY_PICK_CODE, requestPermissionsList);
        // AutoPermissions.Companion.loadSelectedPermissions(this, GALLERY_PICK_CODE, requestPermissionsList);

        // PermissionHelper.Companion.requestPermission(this, GALLERY_PICK_CODE, requestPermissionsList);
        PermissionHelper.Companion.requestAllPermissions(this, GALLERY_PICK_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case GALLERY_PICK_CODE:

                try {
                    if (resultCode == RESULT_OK && data != null && data.getData() != null) {
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
                } catch (Exception e) {
                    Log.e(TAG, "onActivityResult: exception while setting image:\n");
                    e.printStackTrace();
                }

                break;
        }
    }

    public void pickImage(View view) {
        ImagePicker.Companion.with(this)
                .cameraOnly()
                .compress(100)
                .start(GALLERY_PICK_CODE);
    }

    public void setImageWithGlide(View view) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AutoPermissions.Companion.parsePermissions(this, requestCode, permissions, this);
    }

    @Override
    public void onGranted(int requestCode, @NotNull String[] permissions)
    {

    }

    @Override
    public void onDenied(int requestCode, @NotNull String[] permissions)
    {

    }
}
