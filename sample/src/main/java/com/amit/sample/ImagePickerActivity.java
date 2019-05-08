package com.amit.sample;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.amit.dialog.sweetAlert.SweetAlertDialog;
import com.amit.permission.AutoPermissions;
import com.amit.permission.AutoPermissionsListener;
import com.amit.permission.PermissionHelper;
import com.amit.ui.aspinner.ASpinner;

// import com.fxn.pix.Pix;
// import com.github.dhaval2404.imagepicker.ImagePicker;

public class ImagePickerActivity extends AppCompatActivity implements AutoPermissionsListener
{
    private static final int GALLERY_PICK_CODE = 101;
    private final String TAG = ImagePickerActivity.class.getSimpleName();
    private ImageView imgSelectedImage;
    // private PermissionHelper permissionHelper;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
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
        
        PermissionHelper.Companion.requestPermission(this, 100, Manifest.permission.CAMERA);
        PermissionHelper.Companion.requestPermission(this, GALLERY_PICK_CODE, requestPermissionsList);
        PermissionHelper.Companion.requestAllPermissions(this, GALLERY_PICK_CODE);
        
        // JRSpinner jrSpinner = findViewById(R.id.spn_my_spinner);
        // jrSpinner.setExpandTint(R.color.colorPrimary);
        // jrSpinner.setTitle(getResources().getString(R.string.select_gender));
        // jrSpinner.setItems(getResources().getStringArray(R.array.gender_array));
        // jrSpinner.setOnItemClickListener(position -> Log.e(TAG, "onItemClick: selected position is: " + position));
        
        ASpinner mySpinner = findViewById(R.id.mySpinner);
        mySpinner.setExpandTint(R.color.google_plus);
        mySpinner.setTitle(getResources().getString(R.string.select_gender));
        mySpinner.setItems(getResources().getStringArray(R.array.gender_array));
        
        mySpinner.setOnItemClickListener((selectedItem, position) ->
        {
            Log.e(TAG, "onItemClick: selected item is: " + selectedItem);
            Log.e(TAG, "onItemClick: selected item's position is: " + position);
        });
        
        /*mySpinner.setOnSelectMultipleListener((selectedItems, selectedPosition) ->
        {
            if ((selectedItems != null && selectedItems.size() > 0) &&
                    (selectedPosition != null && selectedPosition.size() > 0))
            {
                for (int i = 0; i < selectedItems.size(); i++)
                {
                    Log.e(TAG, "onItemClick: selected item is: " + selectedItems.get(i));
                }

                for (int i = 0; i < selectedPosition.size(); i++)
                {
                    Log.e(TAG, "onItemClick: selected item's position is: " + selectedPosition.get(i));
                }
            }
        });*/
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
    
        if (requestCode == GALLERY_PICK_CODE)
        {
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
        }
    }
    
    public void pickImage(View view)
    {
        /*ImagePicker.Companion.with(this)
                .cameraOnly()
                .compress(100)
                .start(GALLERY_PICK_CODE);*/
        
        SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Login Successful...");
        // pDialog.setContentText(getResources().getString(R.string.success));
        pDialog.setCancelable(false);
        pDialog.show();
    }
    
    public void setImageWithGlide(View view)
    {
    
    }
    
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AutoPermissions.Companion.parsePermissions(this, requestCode, permissions, this);
    }
    
    @Override
    public void onGranted(int requestCode, @NonNull String[] permissions)
    {
    
    }
    
    @Override
    public void onDenied(int requestCode, @NonNull String[] permissions)
    {
    
    }
}
