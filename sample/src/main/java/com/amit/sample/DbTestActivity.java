package com.amit.sample;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ListView;

import com.amit.db.DBHelper;
import com.amit.sample.adapter.UserListCursorAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

public class DbTestActivity extends AppCompatActivity
{
    private static final String TAG = DbTestActivity.class.getSimpleName();
    
    private DBHelper dbHelper;
    
    // private TextView tvUsersTableData;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_test);
        
        dbHelper = new DBHelper(DbTestActivity.this);
        // tvUsersTableData = findViewById(R.id.tvUsersTableData);
        // BottomAppBar bottomAppBar = findViewById(R.id.bottomAppBar);
        
        // bottomAppBar.setOnItemSelectListener(index -> Log.e(TAG, "onItemSelected: selected index is: " + index));
        // PermissionHelper.Companion.requestAllPermissions(this, 120);
        
        // calling create and save data method
        createAndSaveData();
    }
    
    /**
     * 2019 January 09 - Wednesday - 11:49 AM
     * create and save data method
     * <p>
     * this method will create table and save data
     **/
    private void createAndSaveData()
    {
        try
        {
            ListView lvUsersList = findViewById(R.id.lvUsersList);
            
            /*ProgressDialog progressDialog = ProgressDialog.show(this, null, "please wait.....", true, false);
            progressDialog.dismiss();*/
            
            /*ArrayList<User> userArrayList = dbHelper.getAllRecords("User", true, null, User.class);
            
            if (userArrayList != null && userArrayList.size() > 0)
            {
                *//*StringBuilder fullName = new StringBuilder();
                
                for (int i = 0; i < userArrayList.size(); i++)
                {
                    User user = userArrayList.get(i);
                    
                    fullName.append(user.getId()).append(" - ")
                            .append(user.getFirstName())
                            .append(" ")
                            .append(user.getLastName())
                            .append("\n");
                }*//*
                
                // tvUsersTableData.setText(fullName.toString());
                Log.e(TAG, "createAndSaveData: retrieving data completed.");
            }
            else
            {
                Log.e(TAG, "createAndSaveData: data not found.");
            }*/
            
            // creating user table
            /*dbHelper.addColumnForTable(new DbColumns("_id", new String[]{"integer", "primary key", "autoincrement"}))
                    .addColumnForTable(new DbColumns("firstName", "text"))
                    .addColumnForTable(new DbColumns("lastName", "text"))
                    .addColumnForTable(new DbColumns("mobileNo", "text"))
                    .addColumnForTable(new DbColumns("age", "integer"))
                    .addColumnForTable(new DbColumns("height", "real"))
                    .createTable("User");*/
            
            // dbHelper.db.getWritableDatabase().execSQL("DELETE FROM User");
            /*Log.e(TAG, "createAndSaveData: Transaction for 20000 records");
            Log.e(TAG, "createAndSaveData: Db Transaction beginning at: " + System.currentTimeMillis());
            
            JSONObject object = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            
            for (int i = 0; i < 20000; i++)
            {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("firstName", randomStringGenerator());
                jsonObject.put("lastName", randomStringGenerator());
                jsonObject.put("mobileNo", randomMobileNoGenerator());
                jsonObject.put("age", randomAgeGenerator());
                jsonObject.put("height", randomAgeGenerator());
                
                jsonArray.put(jsonObject);
            }
            
            object.put("", jsonArray);
            dbHelper.insertDataWithJsonAndTransaction("User", object, 5);
            Log.e(TAG, "createAndSaveData: Db Transaction ending at: " + System.currentTimeMillis());*/
            
            Cursor cursor = dbHelper.executeSelectQuery("SELECT * FROM User");
            UserListCursorAdapter userListCursorAdapter = new UserListCursorAdapter(this, cursor);
            lvUsersList.setAdapter(userListCursorAdapter);
            
            /*ArrayList<User> userArrayList = dbHelper.getAllRecordsV2(
                    "User", true, null, User.class);
    
            Log.e(TAG, "createAndSaveData: users array list size is: " + userArrayList.size());
            
            UserListBaseAdapter userListBaseAdapter = new UserListBaseAdapter(this, userArrayList);
            lvUsersList.setAdapter(userListBaseAdapter);*/
            
            /*Cursor cursor = dbHelper.executeSelectQuery("SELECT * FROM User");
            
            if (cursor != null && cursor.moveToFirst())
            {
                for (int i = 0; i < cursor.getCount(); i++)
                {
                    Log.e(TAG, "createAndSaveData: first name is: " + cursor.getString(cursor.getColumnIndex("firstName")));
                    Log.e(TAG, "createAndSaveData: last name is: " + cursor.getString(cursor.getColumnIndex("lastName")));
                    Log.e(TAG, "createAndSaveData: mobile no is: " + cursor.getString(cursor.getColumnIndex("mobileNo")));
                    Log.e(TAG, "createAndSaveData: age is: " + cursor.getString(cursor.getColumnIndex("age")));
                    Log.e(TAG, "createAndSaveData: height is: " + cursor.getString(cursor.getColumnIndex("height")));
                    
                    cursor.moveToNext();
                }
            }
            else
            {
                Log.e(TAG, "createAndSaveData: cursor was null or no records found.");
            }*/
            
            // dbHelper.db.getWritableDatabase().beginTransaction();
            // String query = "INSERT INTO User (age, firstName, mobileNo, height, lastName, image) VALUES (?, ?, ?, ?, ?, ?)";
            // SQLiteStatement statement = dbHelper.db.getWritableDatabase().compileStatement(query);

            /*for (int i = 0; i < 20000; i++)
            {
                // inserting records in user table
                *//*statement.bindLong(1, randomAgeGenerator());
                statement.bindString(2, randomStringGenerator());
                statement.bindString(3, randomMobileNoGenerator());
                statement.bindDouble(4, 5.6);
                statement.bindString(5, randomStringGenerator());
                statement.bindBlob(6, randomByteArrayGenerator());

                statement.execute();
                statement.clearBindings();*//*

                dbHelper.addDataForTable(new DbData("age", randomAgeGenerator()))
                        .addDataForTable(new DbData("firstName", randomStringGenerator()))
                        .addDataForTable(new DbData("mobileNo", randomMobileNoGenerator()))
                        .addDataForTable(new DbData("height", 5.6))
                        .addDataForTable(new DbData("lastName", randomStringGenerator()));
                        // .insertData("User");
            }*/
            
            // dbHelper.insertDataWithTransaction("User", 5);
            // Log.e(TAG, "createAndSaveData: Db Transaction ending at: " + System.currentTimeMillis());
            
            // dbHelper.db.getWritableDatabase().setTransactionSuccessful();
            // dbHelper.db.getWritableDatabase().endTransaction();
            
            // calling get database file method
            // getDatabaseFile();
        }
        catch (Exception e)
        {
            Log.e(TAG, "createAndSaveData: exception while creating table and saving records:\n");
            e.printStackTrace();
        }
    }
    
    /**
     * 2019 January 09 - Wednesday - 12:16 PM
     * random age generator method
     * <p>
     * this method will generate random int
     **/
    private int randomAgeGenerator()
    {
        int minAge = 22, maxAge = 26;
        return new Random().nextInt((maxAge - minAge) + 1) + minAge;
    }
    
    /**
     * 2019 January 09 - Wednesday - 12:14 PM
     * random string generator method
     * <p>
     * this method will generate random strings
     **/
    private String randomStringGenerator()
    {
        String DATA = "ABCDEFGHIJKLMONPQRSTUVWXYZ ABCDEFGHIJKLMONPQRSTUVWXYZ";
        Random stringGenerator = new Random();
        StringBuilder randomString = new StringBuilder(6);
        
        for (int i = 0; i < 10; i++)
        {
            randomString.append(DATA.charAt(stringGenerator.nextInt(DATA.length())));
        }
        
        return randomString.toString();

        /*char tempChar;
        int wordLength = 10;
        Random stringGenerator = new Random();
        StringBuilder randomString = new StringBuilder();

        for (int i = 0; i < wordLength; i++)
        {
            tempChar = (char) (stringGenerator.nextInt(wordLength - 3 + 1) + 3);
            randomString.append(tempChar);
        }

        return randomString.toString();*/

        /*int randomNum;
        String first, last, userName;

        Scanner scanner = new Scanner("Amit Jangid");
        first = scanner.next();
        last = scanner.next();

        // Create random number 10-99
        // add 10 so the lowest number generated is 10
        // randomize 90 numbers because 0 counts as a number
        Random generator = new Random();
        randomNum = generator.nextInt(90) + 10;

        // Display custom username
        userName = first.charAt(0) + last.substring(0, 5);
        return userName;*/
    }
    
    /**
     * 2019 January 09 - Wednesday - 12:19 PM
     * random mobile no generator method
     * <p>
     * this method will generate random mobile numbers
     **/
    private String randomMobileNoGenerator()
    {
        int min = 7, max = 9;
        int num1, num2, num3; //3 numbers in area code
        int set2, set3; //sequence 2 and 3 of the phone number
        
        Random mobileNoGenerator = new Random();
        
        //Area code number; Will not print 8 or 9
        num1 = mobileNoGenerator.nextInt((max - min) + 1) + min; //add 1 so there is no 0 to begin
        num2 = mobileNoGenerator.nextInt(8); //randomize to 8 because 0 counts as a number in the generator
        num3 = mobileNoGenerator.nextInt(8);
        
        // Sequence two of phone number
        // the plus 100 is so there will always be a 3 digit number
        // randomize to 643 because 0 starts the first placement so
        // if i randomized up to 642 it would only go up yo 641 plus 100
        // and i used 643 so when it adds 100 it will not succeed 742
        set2 = mobileNoGenerator.nextInt(643) + 100;
        
        //Sequence 3 of numebr
        // add 1000 so there will always be 4 numbers
        //8999 so it wont succeed 9999 when the 1000 is added
        set3 = mobileNoGenerator.nextInt(8999) + 1000;
        
        return num1 + "" + num2 + "" + num3 + "" + set2 + "" + set3;
    }
    
    /*
     * 2019 January 09 - Wednesday - 12:53 PM
     * random byte array generator method
     *
     * this method will generate random bytes array
     **/
    /*private byte[] randomByteArrayGenerator()
    {
        byte[] b = new byte[20];
        new Random().nextBytes(b);
        return b;
    }*/
    
    /**
     * 2019 Mar 30 - Saturday - 01:09 PM
     * get database file method
     * <p>
     * this method will export database file to local storage
     **/
    @SuppressLint("SdCardPath")
    private void getDatabaseFile()
    {
        try
        {
            File sd = Environment.getExternalStorageDirectory();
            
            if (sd.canWrite())
            {
                String currentDBPath = "/data/data/" + getPackageName() + "/databases/USER_DB.db";
                File currentDB = new File(currentDBPath);
                File backupDB = new File(sd, "USER_DB.db");
                
                if (currentDB.exists())
                {
                    Log.e(TAG, "getFilesName: directory exists exporting database file.");
                    
                    FileChannel src = new FileInputStream(currentDB).getChannel();
                    FileChannel dst = new FileOutputStream(backupDB).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    
                    src.close();
                    dst.close();
                }
                else
                {
                    Log.e(TAG, "getFilesName: db file doesn't exists.");
                }
            }
            else
            {
                Log.e(TAG, "getFilesName: external storage permission is required.");
            }
        }
        catch (Exception e)
        {
            Log.e(TAG, "exception while exporting database file");
            e.printStackTrace();
        }
    }
}
