package com.amit.sample;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.amit.cryptography.AESCrypt;
import com.amit.db.DBHelper;
import com.amit.dialog.AlertDialogBox;
import com.amit.dialog.PromptDialogBox;
import com.amit.location.GeoLocation;
import com.amit.sample.model.LibraryTestModel;
import com.amit.shinebtn.ShineButton;
import com.amit.ui.SwitchButton;
import com.amit.utilities.SharedPreferenceData;
import com.location.aravind.getlocation.GeoLocator;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/*import cn.refactor.lib.colordialog.PromptDialog;*/
/*import com.brouding.simpledialog.SimpleDialog;*/
/*import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;*/

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
    // private ProgressBtn progressBtn;

    private TextInputEditText textPassword;
    private Button btnEncryptDecryptPassword;
    private boolean isPasswordEncrypted = false;
    private android.widget.TextView displayFourTextView;

    private GeoLocation geoLocation;

    /*private ArrowDownloadButton downloadButton;*/

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AESCrypt.DEBUG_LOG_ENABLED = true;
        DBHelper dbHelper = new DBHelper(this);

        SharedPreferenceData sharedPreferenceData = new SharedPreferenceData(MainActivity.this);
        sharedPreferenceData.setValue("dbName", "testDB");

        /*final SegmentedControl segmented_control = findViewById(R.id.segmented_control);

        segmented_control.addOnSegmentSelectListener(new OnSegmentSelectedListener()
        {
            @Override
            public void onSegmentSelected(SegmentViewHolder segmentViewHolder, boolean isSelected, boolean isReselected)
            {
                ToastMsg.success(MainActivity.this,
                        "Selected segment is: " + segmented_control.getSelectedViewHolder().getSegmentData() +
                        " and selected segment's position is: " + segmented_control.getSelectedViewHolder().getAbsolutePosition(),
                        ToastMsg.ToastPosition.CENTER).show();
            }
        });*/

        /*try
        {
            GeoLocator geoLocator = new GeoLocator(getApplicationContext(),MainActivity.this);
            Log.e(TAG, "onCreate: address of current location  is: " + geoLocator.getAddress());
            Log.e(TAG, "onCreate: latitude of current location  is: " + geoLocator.getLattitude());
            Log.e(TAG, "onCreate: longitude of current location  is: " + geoLocator.getLongitude());
            Log.e(TAG, "onCreate: known name of current location is: " + geoLocator.getKnownName());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }*/

        try
        {
            geoLocation = new GeoLocation(MainActivity.this);
            Log.e(TAG, "onCreate: address of current location is: " + geoLocation.getAddress());
            Log.e(TAG, "onCreate: latitude of current location is: " + geoLocation.getLatitude());
            Log.e(TAG, "onCreate: longitude of current location is: " + geoLocation.getLongitude());

            String city = geoLocation.getCity();
            String state = geoLocation.getState();
            String country = geoLocation.getCountry();
            String premises = geoLocation.getPremises();
            String knownName = geoLocation.getKnownName();
            String postalCode = geoLocation.getPostalCode();
            String subLocality = geoLocation.getSubLocality();
            String subAdminArea = geoLocation.getSubAdminArea();
            String thoroughFare = geoLocation.getThoroughFare();
            String subThoroughFare = geoLocation.getSubThoroughFare();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        /*String tableName = "Member";
        LinkedHashMap<String, String> values = new LinkedHashMap<>();
        values.put("Code", "INTEGER PRIMARY KEY");
        values.put("AccountNo", "TEXT");
        values.put("GujAccountNo", "TEXT");
        values.put("GujLocName", "TEXT");
        values.put("GujMemberCode", "INTEGER");
        values.put("GujMemberName", "TEXT");
        values.put("LocationCode", "INTEGER");
        values.put("LocationName", "TEXT");
        values.put("MemberCode", "INTEGER");
        values.put("MemberName", "TEXT");

        dbHelper.executeDatabaseOperations(tableName,
                "c",
                values,
                false,
                null);*/

        /*values = new LinkedHashMap<>();
        values.put("Code", DatabaseUtils.sqlEscapeString("1"));
        values.put("AccountNo", DatabaseUtils.sqlEscapeString("2"));
        values.put("GujAccountNo", DatabaseUtils.sqlEscapeString("3"));
        values.put("GujLocName", DatabaseUtils.sqlEscapeString("4"));
        values.put("GujMemberCode", DatabaseUtils.sqlEscapeString("5"));
        values.put("GujMemberName", DatabaseUtils.sqlEscapeString("6"));
        values.put("LocationCode", DatabaseUtils.sqlEscapeString("7"));
        values.put("LocationName", DatabaseUtils.sqlEscapeString("8"));
        values.put("MemberCode", DatabaseUtils.sqlEscapeString("9"));
        values.put("MemberName", DatabaseUtils.sqlEscapeString("10"));

        dbHelper.executeDatabaseOperations(tableName,
                "i",
                values,
                false,
                null);*/

        /*try
        {
            ArrayList<Model_MemberList> modelMemberListArrayList = dbHelper.executeSelectQuery(
                    tableName, "*",
                    false,
                    null,
                    Model_MemberList.class);

            if (modelMemberListArrayList != null)
            {
                for (int i = 0; i < modelMemberListArrayList.size(); i++)
                {
                    Log.e(TAG, "onCreate: values in array list is: " + modelMemberListArrayList.get(i));
                    Log.e(TAG, "onCreate: location Name is: " + modelMemberListArrayList.get(i).getLocationName());
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        tableName = "LibraryTest";
        values = new LinkedHashMap<>();
        values.put("code", "INTEGER PRIMARY KEY");
        values.put("value", "REAL");
        values.put("locked", "INTEGER");
        values.put("description", "TEXT");

        dbHelper.executeDatabaseOperations(tableName,
                "c",
                values,
                false,
                null);*/

        /*for (int i = 0; i < 5; i++)
        {
            values = new LinkedHashMap<>();
            values.put("code", DatabaseUtils.sqlEscapeString(String.valueOf(i + 1)));
            values.put("value", DatabaseUtils.sqlEscapeString(String.valueOf((i + 1) + ".0")));
            values.put("locked", DatabaseUtils.sqlEscapeString("0"));
            values.put("description", DatabaseUtils.sqlEscapeString("This is test data no: " + (i + 1)));

            dbHelper.executeDatabaseOperations(tableName,
                    "i",
                    values,
                    false,
                    null);
        }*/

        // String tableName = "LibraryTest";

        /*ArrayList<LibraryTestModel> libraryTestModels = dbHelper.executeSelectQuery(
                tableName, "*",
                false,
                null,
                LibraryTestModel.class);*/

        /*ArrayList<LibraryTestModel> libraryTestModels = dbHelper.executeSelectQuery(
                tableName,
                "*",
                false,
                "code = 1",
                LibraryTestModel.class);

        if (libraryTestModels != null)
        {
            for (int i = 0; i < libraryTestModels.size(); i++)
            {
                Log.e(TAG, "onCreate: code's value is: " + libraryTestModels.get(i).getCode());
                Log.e(TAG, "onCreate: value's value is: " + libraryTestModels.get(i).getValue());
                Log.e(TAG, "onCreate: locked's value is: " + libraryTestModels.get(i).isLocked());
                Log.e(TAG, "onCreate: description's value is: " + libraryTestModels.get(i).getDescription());
            }
        }

        LinkedHashMap<String, String> values = new LinkedHashMap<>();
        values.put("code", "10");
        values.put("value", "10.1");
        values.put("locked", "0");
        values.put("description", "Insert with transaction");

        Log.e(TAG, "onCreate: insert with transaction start time is: " + System.currentTimeMillis());
        dbHelper.executeInsertWithTransaction(tableName, values);
        Log.e(TAG, "onCreate: insert with transaction end time is: " + System.currentTimeMillis());

        values = new LinkedHashMap<>();
        values.put("code", "20");
        values.put("value", "20.1");
        values.put("locked", "1");
        values.put("description", "'Insert without transaction.'");

        Log.e(TAG, "onCreate: insert without transaction start time is: " + System.currentTimeMillis());
        dbHelper.executeDatabaseOperations(tableName,
                "i",
                values,
                false,
                null);
        Log.e(TAG, "onCreate: insert without transaction end time is: " + System.currentTimeMillis());*/

        /*ArrayList<StyleDetails> styleDetailsArrayList = dbHelper.executeSelectQuery(
                "StyleDetails",
                "*",
                false,
                null);

        Log.e(TAG, "onCreate: style details array list is: " + styleDetailsArrayList);*/

        ((SwitchButton) findViewById(R.id.switchButton)).setOnSwitchListener(new SwitchButton.OnSwitchListener()
        {
            @Override
            public void onSwitch(int position, String tabText)
            {
                PromptDialogBox promptDialogBox = new PromptDialogBox(MainActivity.this)
                        .setDialogType(PromptDialogBox.DIALOG_TYPE_ERROR)
                        .setAnimationEnable(true)
                        .setTitleText(getResources().getString(R.string.error))
                        .setContentText(getResources().getString(R.string.app_name))
                        .setPositiveListener("YES", new PromptDialogBox.onPositiveListener()
                        {
                            @Override
                            public void onClick(PromptDialogBox dialog)
                            {
                                dialog.dismiss();
                            }
                        });

                promptDialogBox.setCancelable(false);
                promptDialogBox.show();
            }
        });


        textPassword = findViewById(R.id.textPassword);
        btnEncryptDecryptPassword = findViewById(R.id.btnEncryptDecryptPassword);

        /*try
        {
            displayFourTextView.setText("Testing for exception and getting stack trace");
        }
        catch(Exception e)
        {
            Log.e(TAG, "Exception occurred.");
            e.printStackTrace();
        }*/

        final ShineButton shineButton = findViewById(R.id.shine_button);
        shineButton.init(MainActivity.this);

        shineButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                /*PromptDialog promptDialog = new PromptDialog(MainActivity.this)
                        .setDialogType(PromptDialog.DIALOG_TYPE_SUCCESS)
                        .setAnimationEnable(true)
                        .setTitleText(getString(R.string.success))
                        .setContentText(getString(R.string.long_text))
                        .setPositiveListener(getString(R.string.ok), new PromptDialog.OnPositiveListener()
                        {
                            @Override
                            public void onClick(PromptDialog dialog)
                            {
                                dialog.dismiss();
                            }
                        });

                promptDialog.setCancelable(false);
                promptDialog.show();*/

                /*ColorDialog dialog = new ColorDialog(MainActivity.this);
                dialog.setTitle("Color Dialog");
                dialog.setContentText("This is a third party color dialog.");
                dialog.setContentImage(getResources().getDrawable(R.drawable.ic_action_accept));
                dialog.setPositiveListener("OK", new ColorDialog.onPositiveListener()
                {
                    @Override
                    public void onClick(ColorDialog dialog)
                    {
                        Toast.makeText(MainActivity.this, dialog.getPositiveText().toString(), Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeListener(getString(R.string.cancel), new ColorDialog.OnNegativeListener()
                {
                            @Override
                            public void onClick(ColorDialog dialog)
                            {
                                Toast.makeText(MainActivity.this, dialog.getNegativeText().toString(), Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }

                        }).show();*/

                /*new AlertDialogBox.Builder(MainActivity.this)
                        .setTitle("Custom Dialog")
                        .setBackgroundColor(Color.parseColor("#909090"))  //Don't pass R.color.colorvalue
                        .setMessage("This is a custom dialog with buttons callback.")
                        .setNegativeBtnText("Cancel")
                        .setNegativeBtnTextColor(getResources().getColor(R.color.black_shade))
                        .setPositiveBtnBackground(Color.parseColor("#FF4081"))  //Don't pass R.color.colorvalue
                        .setPositiveBtnText("Ok")
                        .setPositiveBtnTextColor(getResources().getColor(R.color.btnColor))
                        .setNegativeBtnBackground(Color.parseColor("#FFA9A7A8"))  //Don't pass R.color.colorvalue
                        .setAnim(Anim.POP)
                        .isCancellable(false)
                        .setIcon(R.drawable.ic_error_outline_white_48dp, Icon.Visible)
                        .onPositiveClicked(new AlertDialogListener() {
                            @Override
                            public void onClick() {
                                Toast.makeText(getApplicationContext(), "Ok", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .onNegativeClicked(new AlertDialogListener() {
                            @Override
                            public void onClick() {
                                Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .build();*/

                /*new FancyAlertDialog.Builder(MainActivity.this)
                        .setTitle("Rate us if you like the app")
                        .setBackgroundColor(Color.parseColor("#303F9F"))  //Don't pass R.color.colorvalue
                        .setMessage("Do you really want to Exit ?")
                        .setNegativeBtnText("Cancel")
                        .setPositiveBtnBackground(Color.parseColor("#FF4081"))  //Don't pass R.color.colorvalue
                        .setPositiveBtnText("Rate")
                        .setNegativeBtnBackground(Color.parseColor("#FFA9A7A8"))  //Don't pass R.color.colorvalue
                        .setAnimation(Animation.SLIDE)
                        .isCancellable(true)
                        .setIcon(R.drawable.ic_person_black_24dp, Icon.Visible)
                        .onPositiveClicked(new FancyAlertDialogListener()
                        {
                            @Override
                            public void OnClick()
                            {
                                Toast.makeText(getApplicationContext(),"Rate",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .onNegativeClicked(new FancyAlertDialogListener()
                        {
                            @Override
                            public void OnClick()
                            {
                                Toast.makeText(getApplicationContext(),"Cancel",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .build();*/

                /*new SimpleDialog.Builder(MainActivity.this)
                        .setTitle("Hello!!")
                        .setContent("This is a simple dialog with callback.")
                        .setBtnConfirmText("OKAY")
                        .setBtnConfirmTextColor(R.color.colorPrimary)
                        .onConfirm(new SimpleDialog.BtnCallback()
                        {
                            @Override
                            public void onClick(@NonNull SimpleDialog dialog, @NonNull SimpleDialog.BtnAction which)
                            {
                                Log.e(TAG, "onClick: OKAY button was clicked.");
                            }
                        })
                        .setBtnCancelText("Cancel")
                        .setBtnCancelTextColor(R.color.gray)
                        .onCancel(new SimpleDialog.BtnCallback()
                        {
                            @Override
                            public void onClick(@NonNull SimpleDialog dialog, @NonNull SimpleDialog.BtnAction which)
                            {
                                dialog.dismiss();
                                Log.e(TAG, "onClick: Cancel button was clicked.");
                            }
                        })
                        .show();*/


                /*String key = "zjcSX3TumLzbJfpW\\\\/Zzung==";
                String padding = "AES/CBC/PKCS5Padding";
                String value = "AMIT JANGID";

                String encryptedString = encrypt(key, padding, value);
                Log.e(TAG, "onClick: encrypted value is: " + encryptedString);

                String time = Utils.getTimeWithAMPM(5, 22);
                Log.e(TAG, "onClick: time is: " + time);*/

                /*ToastMsg.info(MainActivity.this,
                        "Info toast message.",
                        Toast.LENGTH_LONG,
                        true).show();*/

                /*ToastMsg.warning(MainActivity.this,
                        "Warning toast message.",
                        Toast.LENGTH_LONG,
                        true).show();*/

                /*ToastMsg.error(MainActivity.this,
                        "Error toast message.",
                        Toast.LENGTH_LONG,
                        true).show();*/

                /*ToastMsg.success(MainActivity.this,
                        "Success toast message.",
                        Toast.LENGTH_LONG,
                        true).show();*/

                /*ToastMsg.normal(MainActivity.this,
                        "Normal toast message.",
                        Toast.LENGTH_LONG,
                        getResources().getDrawable(R.drawable.ic_action_accept),
                        true).show();*/

                /*ToastMsg.custom(MainActivity.this,
                        "This is a custom toast message.",
                        R.drawable.ic_check_white_48dp,
                        getResources().getColor(R.color.colorPrimary),
                        Toast.LENGTH_LONG,
                        true,
                        true,
                        ToastMsg.ToastPosition.CENTER).show();*/

                /**
                 *
                 * Cookie Bar 2
                 *
                 * https://github.com/AviranAbady/CookieBar2?utm_source=android-arsenal.com&utm_medium=referral&utm_campaign=6122
                 *
                 * implementation 'org.aviran.cookiebar2:cookiebar2:1.0.9'
                 *
                 * */
            }
        });

        /*progressBtn = findViewById(R.id.progressBtn);
        progressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialogBox.Builder(MainActivity.this)
                        .setTitle("Custom Dialog")
                        .setBackgroundColor(Color.parseColor("#909090"))  //Don't pass R.color.colorvalue
                        .setMessage("This is a custom dialog with buttons callback.")
                        .setNegativeBtnText("Cancel")
                        .setNegativeBtnTextColor(getResources().getColor(R.color.black_shade))
                        .setPositiveBtnBackground(Color.parseColor("#FF4081"))  //Don't pass R.color.colorvalue
                        .setPositiveBtnText("Ok")
                        .setPositiveBtnTextColor(getResources().getColor(R.color.btnColor))
                        .setNegativeBtnBackground(Color.parseColor("#FFA9A7A8"))  //Don't pass R.color.colorvalue
                        .setAnim(AlertDialogBox.Anim.POP)
                        .isCancellable(false)
                        .setIcon(R.drawable.ic_error_outline_white_48dp, AlertDialogBox.Icon.Visible)
                        .onPositiveClicked(new AlertDialogBox.AlertDialogListener() {
                            @Override
                            public void onClick() {
                                Toast.makeText(getApplicationContext(), "Ok", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .onNegativeClicked(new AlertDialogBox.AlertDialogListener() {
                            @Override
                            public void onClick() {
                                Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .build();

                progressBtn.startAnimation();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressBtn.revertAnimation();
                    }
                }, 2500);
            }
        });*/

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
     * <p>
     * https://github.com/nihasKalam07/ProgressButton?utm_source=android-arsenal.com&utm_medium=referral&utm_campaign=4913
     * <p>
     * implementation 'com.nihaskalam.android:progress-button:1.0.1'
     **/

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        // progressBtn.dispose();
    }

    public void showPromptDialog(View view)
    {
        new AlertDialogBox.Builder(MainActivity.this)
                .setTitle("Custom Dialog")
                .setBackgroundColor(Color.parseColor("#909090"))  //Don't pass R.color.colorvalue
                .setMessage("This is a custom dialog with buttons callback.")
                .setNegativeBtnText("Cancel")
                .setNegativeBtnTextColor(getResources().getColor(R.color.black_shade))
                .setPositiveBtnBackground(Color.parseColor("#FF4081"))  //Don't pass R.color.colorvalue
                .setPositiveBtnText("Ok")
                .setPositiveBtnTextColor(getResources().getColor(R.color.btnColor))
                .setNegativeBtnBackground(Color.parseColor("#FFA9A7A8"))  //Don't pass R.color.colorvalue
                .setAnim(AlertDialogBox.Anim.POP)
                .isCancellable(false)
                .setIcon(R.drawable.ic_error_outline_white_48dp, AlertDialogBox.Icon.Visible)
                .onPositiveClicked(new AlertDialogBox.AlertDialogListener() {
                    @Override
                    public void onClick()
                    {
                        Toast.makeText(getApplicationContext(), "Ok", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, SaveUpdateDataActivity.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.anim_stay);
                    }
                })
                .onNegativeClicked(new AlertDialogBox.AlertDialogListener() {
                    @Override
                    public void onClick() {
                        Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_SHORT).show();
                    }
                })
                .build();

        /*new PromptDialogBox(MainActivity.this)
                .setDialogType(PromptDialogBox.DIALOG_TYPE_WARNING)
                .setAnimationEnable(true)
                .setTitleText("Prompt Dialog")
                .setContentText(getResources().getString(R.string.long_text))
                .setPositiveListener(getResources().getString(R.string.ok), new PromptDialogBox.onPositiveListener()
                {
                    @Override
                    public void onClick(PromptDialogBox dialog)
                    {
                        dialog.dismiss();
                    }
                })
                .show();*/
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        if (isPasswordEncrypted)
        {
            btnEncryptDecryptPassword.setText(getResources().getString(R.string.decrypt_password));
        }
        else
        {
            btnEncryptDecryptPassword.setText(getResources().getString(R.string.encrypt_password));
        }
    }

    public void encryptDecryptPassword(View view)
    {
        try
        {
            if (btnEncryptDecryptPassword.getText().toString().equalsIgnoreCase(getResources().getString(R.string.encrypt_password)))
            {
                String cipherText = AESCrypt.encrypt(
                        getResources().getString(R.string.app_name),
                        textPassword.getText().toString().trim());

                textPassword.setText("");
                textPassword.setText(cipherText);

                Log.e(TAG, "encryptDecryptPassword: cipher text is: " + cipherText);
                btnEncryptDecryptPassword.setText(getResources().getString(R.string.decrypt_password));
            }
            else if (btnEncryptDecryptPassword.getText().toString().equalsIgnoreCase(getResources().getString(R.string.decrypt_password)))
            {
                String password = AESCrypt.decrypt(
                        getResources().getString(R.string.app_name),
                        textPassword.getText().toString().trim());

                textPassword.setText("");
                textPassword.setText(password);

                Log.e(TAG, "encryptDecryptPassword: password recovered is: " + password);
                btnEncryptDecryptPassword.setText(getResources().getString(R.string.encrypt_password));
            }
        }
        catch (Exception e)
        {
            Log.e(TAG, "encryptDecryptPassword: exception occurred: ", e);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        switch (requestCode)
        {
            case GeoLocation.REQUEST_LOCATION:

                geoLocation = new GeoLocation(MainActivity.this);
                Log.e(TAG, "onRequestPermissionsResult: address is: " + geoLocation.getAddress());

                break;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
