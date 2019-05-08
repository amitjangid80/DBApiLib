package com.amit.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.contentarcade.adnan.shapedblurlibrary.GaussianBlur;
import com.contentarcade.adnan.shapedblurlibrary.view.ShapeLayout;

public class MultiShapeBlurView extends AppCompatActivity
{
    private final String TAG = MultiShapeBlurView.class.getSimpleName();
    
    private ImageView imgBlurredImage;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_shape_blur_view);
        
        // calling init activity method
        initActivity();
        
        // calling apply blur method
        applyBlur();
    }
    
    /**
     * 2019 May 08 - Wednesday - 10:41 AM
     * init activity method
     *
     * this method will initialize views of the activity
    **/
    private void initActivity()
    {
        try
        {
            ImageView imgActualImage = findViewById(R.id.imgActualImage);
            imgBlurredImage = findViewById(R.id.imgBlurredImage);
    
            ShapeLayout shapeLayoutImage = findViewById(R.id.shapeLayoutImage);
            shapeLayoutImage.setTypeOfShape(ShapeLayout.ShapeType.CIRCLE);
            
            imgActualImage.setImageDrawable(getResources().getDrawable(R.drawable.charlie));
        }
        catch (Exception e)
        {
            Log.e(TAG, "initActivity: exception while initializing activity:\n");
            e.printStackTrace();
        }
    }
    
    private void applyBlur()
    {
        GaussianBlur.with(this)
                .size(GaussianBlur.MAX_SIZE)
                .radius(5)
                .put(R.drawable.charlie, imgBlurredImage);
    
        imgBlurredImage.setImageDrawable(getResources().getDrawable(R.drawable.charlie));
    }
    
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        System.gc();
    }
}
