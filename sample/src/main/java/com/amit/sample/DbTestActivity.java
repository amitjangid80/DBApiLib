package com.amit.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.amit.db.DBHelper;
import com.amit.db.DbData;

import java.util.Random;

public class DbTestActivity extends AppCompatActivity
{
    private static final String TAG = DbTestActivity.class.getSimpleName();

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_test);

        dbHelper = new DBHelper(DbTestActivity.this);

        // calling create and save data method
        createAndSaveData();
    }

    /**
     * 2019 January 09 - Wednesday - 11:49 AM
     * create and save data method
     *
     * this method will create table and save data
    **/
    private void createAndSaveData()
    {
        try
        {
            // creating user table
            /*dbHelper.addColumnForTable(new DbColumns("ID", new String[]{"integer", "primary key", "autoincrement"}))
                    .addColumnForTable(new DbColumns("firstName", "text"))
                    .addColumnForTable(new DbColumns("lastName", "text"))
                    .addColumnForTable(new DbColumns("mobileNo", "text"))
                    .addColumnForTable(new DbColumns("age", "integer"))
                    .addColumnForTable(new DbColumns("height", "real"))
                    .addColumnForTable(new DbColumns("image", "blob"))
                    .createTable("User");*/

            // dbHelper.db.getWritableDatabase().execSQL("DELETE FROM User");

            Log.e(TAG, "createAndSaveData: Transaction for 20000 records");
            Log.e(TAG, "createAndSaveData: Db Transaction beginning at: " + System.currentTimeMillis());

            // dbHelper.db.getWritableDatabase().beginTransaction();
            String query = "INSERT INTO User (age, firstName, mobileNo, height, lastName, image) VALUES (?, ?, ?, ?, ?, ?)";
            // SQLiteStatement statement = dbHelper.db.getWritableDatabase().compileStatement(query);

            for (int i = 0; i < 5; i++)
            {
                // inserting records in user table
                /*statement.bindLong(1, randomAgeGenerator());
                statement.bindString(2, randomStringGenerator());
                statement.bindString(3, randomMobileNoGenerator());
                statement.bindDouble(4, 5.6);
                statement.bindString(5, randomStringGenerator());
                statement.bindBlob(6, randomByteArrayGenerator());

                statement.execute();
                statement.clearBindings();*/

                dbHelper.addDataForTable(new DbData("age", randomAgeGenerator()))
                        .addDataForTable(new DbData("firstName", randomStringGenerator()))
                        .addDataForTable(new DbData("mobileNo", randomMobileNoGenerator()))
                        .addDataForTable(new DbData("height", 5.6))
                        .addDataForTable(new DbData("lastName", randomStringGenerator()))
                        .insertData("User");
            }

            Log.e(TAG, "createAndSaveData: Db Transaction ending at: " + System.currentTimeMillis());

            // dbHelper.db.getWritableDatabase().setTransactionSuccessful();
            // dbHelper.db.getWritableDatabase().endTransaction();
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
     *
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
     *
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
     *
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

    /**
     * 2019 January 09 - Wednesday - 12:53 PM
     * random byte array generator method
     *
     * this method will generate random bytes array
    **/
    private byte[] randomByteArrayGenerator()
    {
        byte[] b = new byte[20];
        new Random().nextBytes(b);
        return b;
    }
}
