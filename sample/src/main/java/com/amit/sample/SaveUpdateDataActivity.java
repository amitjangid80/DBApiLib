package com.amit.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.amit.sample.db.AppDatabase;
import com.amit.sample.model.StyleDetails;

import java.util.Date;
import java.util.Random;

public class SaveUpdateDataActivity extends AppCompatActivity
{
    private static final String TAG = SaveUpdateDataActivity.class.getSimpleName();
    public static final String STYLE_CODE = "code";

    private AppDatabase mDb;
    private int mStyleCode;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_update_data);

        mDb = AppDatabase.getInstance(getApplicationContext());

        Intent intent = getIntent();

        if (intent != null && intent.hasExtra(STYLE_CODE))
        {
            mStyleCode = intent.getIntExtra(STYLE_CODE, -1);
        }
    }

    // inserting data
    private void insertStyleData()
    {
        try
        {
            int random = new Random().nextInt(999 - 1 + 1);
            Log.e(TAG, "insertStyleData: rand number for inserting is: " + random);

            int active = 1;
            int styleSyncStatus = 1;

            String styleNo = randomStr();
            Log.e(TAG, "insertStyleData: style no randomly generated is: " + styleNo);

            String styImgFY = "abc123FY-" + styleNo;
            String styImgHY = "abc123HY-" + styleNo;
            String styImgFW = "abc123FW-" + styleNo;
            String styImgHW = "abc123HW-" + styleNo;

            Date createdOn = new Date();
            Date updatedOn = new Date();

            final StyleDetails styleDetail = new StyleDetails(active, random, styleSyncStatus,
                    styleNo, styImgFY, styImgHY, styImgFW, styImgHW, createdOn, updatedOn);

            AppExecutors.getInstance().diskIO().execute(new Runnable()
            {
                @Override
                public void run()
                {
                    mDb.styleDetailsDao().insertStyleDetail(styleDetail);
                    finish();
                }
            });
        }
        catch (Exception e)
        {
            Log.e(TAG, "insertStyleData: exception while inserting style details data.", e);
        }
    }

    public void insertStyleData(View view)
    {
        insertStyleData();
    }

    // updating data
    private void updateStyleData()
    {
        try
        {
            int random = new Random().nextInt(999 - 1 + 1);
            Log.e(TAG, "updateStyleData: random number for updating is: " + random);

            int active = 1;
            // int styleCode = 12345;
            int styleSyncStatus = 1;

            String styleNo = randomStr();
            Log.e(TAG, "updateStyleData: style no generated randomly is: " + styleNo);

            String styImgFY = "abc123FY-" + styleNo;
            String styImgHY = "abc123HY-" + styleNo;
            String styImgFW = "abc123FW-" + styleNo;
            String styImgHW = "abc123HW-" + styleNo;

            Date createdOn = new Date();
            Date updatedOn = new Date();

            final StyleDetails styleDetail = new StyleDetails(active, random,
                    styleSyncStatus, styleNo, styImgFY, styImgHY, styImgFW, styImgHW,
                    createdOn, updatedOn);

            AppExecutors.getInstance().diskIO().execute(new Runnable()
            {
                @Override
                public void run()
                {
                    styleDetail.setCode(mStyleCode);
                    mDb.styleDetailsDao().updateStyleDetail(styleDetail);
                    finish();
                }
            });
        }
        catch (Exception e)
        {
            Log.e(TAG, "updateStyleData: exception while updating style details.", e);
        }
    }

    // random string
    private String randomStr()
    {
        Random generator = new Random();
        char[] chars1 = "ABCDEF012GHIJKL345MNOPQR678STUVWXYZ9".toCharArray();
        StringBuilder randomStrBuilder = new StringBuilder();

        // int randomLength = generator.nextInt(26);
        char tempChar;

        for (int i = 0; i < 10; i++)
        {
            // tempChar = (char) (generator.nextInt(96) + 32);
            tempChar = chars1[generator.nextInt(chars1.length)];
            randomStrBuilder.append(tempChar);
        }

        return randomStrBuilder.toString();
    }

    public void updateStyleData(View view)
    {
        updateStyleData();
    }
}
