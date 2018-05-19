package com.amit.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/*import com.uniquestudio.library.CircleCheckBox;*/

/*import com.github.angads25.toggle.LabeledSwitch;
import com.github.angads25.toggle.interfaces.OnToggledListener;*/

/*import com.fenjuly.library.ArrowDownloadButton;*/
/*import com.sackcentury.shinebuttonlib.ShineButton;*/

public class MainActivity extends AppCompatActivity
{
    private static final String TAG = MainActivity.class.getSimpleName();

    private int count = 0;
    private int progress = 0;
    /*private ArrowDownloadButton downloadButton;*/

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Circle check box
         *
         * https://github.com/CoXier/CheckBox?utm_source=android-arsenal.com&utm_medium=referral&utm_campaign=4357
         *
         * implementation 'com.uniquestudio:checkbox:1.0.10'
         *
         * */
        /*CircleCheckBox checkBox = findViewById(R.id.circle_check_box);
        checkBox.setListener(new CircleCheckBox.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(boolean isChecked)
            {
                // do something
            }
        });*/

        /**
         * labeled switch
         *
         * https://github.com/Angads25/android-toggle?utm_source=android-arsenal.com&utm_medium=referral&utm_campaign=6778
         *
         * implementation 'com.github.angads25:toggle:1.0.0'
         *
         * */
        /*LabeledSwitch labeledSwitch = findViewById(R.id.switchBtn);
        labeledSwitch.setEnabled(false);*/
        /*labeledSwitch.setOnToggledListener(new OnToggledListener()
        {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn)
            {
                // Implement your switching logic here
            }
        });*/

        /**
         *
         * Download button with arrow
         *
         * https://github.com/fenjuly/ArrowDownloadButton?utm_source=android-arsenal.com&utm_medium=referral&utm_campaign=2307
         *
         * implementation 'com.github.fenjuly:ArrowDownloadButton:9e15b85e8a'
         *
         * */
        /*downloadButton = findViewById(R.id.arrow_download_button);
        downloadButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if ((count % 2) == 0)
                {
                    downloadButton.startAnimating();
                    Timer timer = new Timer();

                    timer.schedule(new TimerTask()
                    {
                        @Override
                        public void run()
                        {
                            runOnUiThread(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    progress = progress + 1;
                                    downloadButton.setProgress(progress);
                                }
                            });
                        }
                    }, 2000, 20);
                }
                else
                {
                    downloadButton.reset();
                }

                count++;
            }
        });*/

        /**
         * spark button link
         *
         * implementation 'com.github.varunest:sparkbutton:1.0.5'
         *
         * https://android-arsenal.com/details/1/3876
         *
         * */

        /**
         * submit button link
         * https://github.com/SparkYuan/SubmitButton
         *
         * repositories {
         maven {
         url 'https://dl.bintray.com/spark/maven'
         }
         }
         *
         *
         * implementation 'me.spark:submitbutton:1.0.1'
         * */

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
        /*
         * implementation 'com.github.savvyapps:ToggleButtonLayout:latest.version.here'
         * https://github.com/savvyapps/ToggleButtonLayout/tree/java
         * */

        /**
         * loading button
         * https://github.com/rasoulmiri/ButtonLoading?utm_source=android-arsenal.com&utm_medium=referral&utm_campaign=6394
         **/

        /**
         * share button
         *
         * https://android-arsenal.com/details/1/4503
         **/

        /**
        * Shine button
        *
        * implementation 'com.sackcentury:shinebutton:0.2.0'
        *
        * https://github.com/ChadCSong/ShineButton?utm_source=android-arsenal.com&utm_medium=referral&utm_campaign=3846
        *
        * */
        /*ShineButton shineButton = (ShineButton) findViewById(R.id.shine_button);
        shineButton.init(MainActivity.this);*/

        /**
         *
         * https://android-arsenal.com/details/1/3800
         *
         * https://android-arsenal.com/details/1/3799
         *
         * https://android-arsenal.com/tag/13?page=2&sort=created
         *
         * */
    }

    /**
     * circular progress button
     *
     * https://github.com/nihasKalam07/ProgressButton?utm_source=android-arsenal.com&utm_medium=referral&utm_campaign=4913
     *
     * implementation 'com.nihaskalam.android:progress-button:1.0.1'
     *
     **/
}
