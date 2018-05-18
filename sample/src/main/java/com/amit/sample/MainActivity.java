package com.amit.sample;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.amit.animation.AnimUtil;
import com.amit.ui.CircularProgressButton;
import com.amit.ui.ProgressBtn;
import com.amit.ui.ToastMsg;
import com.amit.ui.Toggle;
import com.amit.ui.ToggleButtonLayout;

public class MainActivity extends AppCompatActivity
{
    ProgressBtn progressBtn;
    ToggleButtonLayout toggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBtn = findViewById(R.id.progressBtn);

        progressBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                progressBtn.startAnimation();
                Handler handler = new Handler();

                handler.postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        Drawable drawable = getResources().getDrawable(R.drawable.ic_check_white_48dp);
                        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                        progressBtn.doneLoadingAnimation(getResources().getColor(R.color.green), bitmap);
                        // progressBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_green_bg));

                        /*progressBtn.revertAnimation(new OnAnimEndListener()
                        {
                            @Override
                            public void onAnimationEnd()
                            {
                                progressBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_green_bg));
                            }
                        });*/
                    }
                }, 2000);

                /*handler.postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        progressBtn.revertAnimation();
                    }
                }, 3000);*/
            }
        });

        final Button bounceButton = findViewById(R.id.btnBounce);
        bounceButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AnimUtil.bounceAnim(MainActivity.this, bounceButton);

                /*
                Simple Dialog Dependency
                implementation 'com.brouding:android-simple-dialog:0.3.1'

                new SimpleDialog.Builder(MainActivity.this)
                        .setContent("This is a progress simple dialog.")
                        .showProgress(true)
                        .setBtnCancelText("Cancel")
                        .setBtnCancelShowTime(5000)
                        .setBtnCancelTextColor(R.color.colorPrimary)
                        .show();*/
            }
        });

        /*
        * implementation 'com.github.savvyapps:ToggleButtonLayout:latest.version.here'
        * https://github.com/savvyapps/ToggleButtonLayout/tree/java
        * */
        toggleButton = findViewById(R.id.toggle);
        toggleButton.OnToggleSelectedListener(new ToggleButtonLayout.OnToggledListener()
        {
            @Override
            public void onToggled(Toggle toggle, boolean selected)
            {
                ToastMsg.info(MainActivity.this, "Selected toggle id is: " + toggle.getId()).show();
            }
        });

        /**
         * loading button
         * https://github.com/rasoulmiri/ButtonLoading?utm_source=android-arsenal.com&utm_medium=referral&utm_campaign=6394
        **/

        /**
         * share button
         *
         * https://android-arsenal.com/details/1/4503
        **/

        circularProgressButton = findViewById(R.id.circularButton3);
        circularProgressButton.setIndeterminateProgressMode(true);
        circularProgressButton.setStrokeColor(ContextCompat.getColor(this, R.color.colorAccent));
    }

    CircularProgressButton circularProgressButton;

    /**
     * circular progress button
     *
     * https://github.com/nihasKalam07/ProgressButton?utm_source=android-arsenal.com&utm_medium=referral&utm_campaign=4913
     *
     * implementation 'com.nihaskalam.android:progress-button:1.0.1'
     *
    **/
    public void progressButtonClick(View view)
    {
        if (circularProgressButton.isIdle())
        {
            circularProgressButton.showProgress();
        }
        else if (circularProgressButton.isErrorOrCompleteOrCancelled())
        {
            circularProgressButton.showIdle();
        }
        else if (circularProgressButton.isProgress())
        {
            circularProgressButton.showCancel();
        }
    }
}
