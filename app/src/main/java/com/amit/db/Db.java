package com.amit.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by AMIT JANGID on 04/01/2019.
**/
@SuppressWarnings({"unused", "FieldCanBeLocal"})
public class Db extends SQLiteOpenHelper
{
    private static final String TAG = Db.class.getSimpleName();

    private StringBuilder SQL = new StringBuilder();
    private String DATABASE_NAME, TABLE_NAME = "DEMO_TABLE";
    private ArrayList<DbColumns> dbColumnsArrayList = new ArrayList<>();

    private SQLiteDatabase db;
    private ContentValues mContentValues = new ContentValues();

    private boolean isDbInitialized = false;

    private void initDb()
    {
        db = getWritableDatabase();
        isDbInitialized = true;
    }

    public Db addData(int columnNumber, String data)
    {
        if (!isDbInitialized || db == null)
        {
            initDb();
        }

        mContentValues.put(dbColumnsArrayList.get(columnNumber - 1).mColumnName, data);
        return this;
    }

    public Db addData(String columnName, String data)
    {
        columnName = columnName.replaceAll(" ", "_");

        if (!isDbInitialized || db == null)
        {
            initDb();
        }

        mContentValues.put(columnName, data);
        return this;
    }

    private Db addData(int columnNumber, int data)
    {
        if (!isDbInitialized || db == null)
        {
            initDb();
        }

        mContentValues.put(dbColumnsArrayList.get(columnNumber -1).mColumnName, data);
        return this;
    }

    public Db addData(String columnName, int data)
    {
        columnName = columnName.replaceAll(" ", "_");

        if (!isDbInitialized || db == null)
        {
            initDb();
        }

        mContentValues.put(columnName, data);
        return this;
    }

    public boolean doneAddingData()
    {
        long result = db.insert(TABLE_NAME, null, mContentValues);
        mContentValues = new ContentValues();
        return result != -1;
    }

    public Cursor getAllData()
    {
        if (!isDbInitialized || db == null)
        {
            initDb();
        }

        String query = "SELECT * FROM " + TABLE_NAME;
        return db.rawQuery(query, null);
    }

    public Cursor getAllDataOrderBy(int columnNumber, boolean ascending)
    {
        String orderBy = ascending ? "" : " DESC " ;
        String columnName = columnNumber == 0 ? " ID " : dbColumnsArrayList.get(columnNumber - 1).mColumnName;

        if (!isDbInitialized || db == null)
        {
            initDb();
        }

        String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + columnName + orderBy;
        return db.rawQuery(query, null);
    }

    public Cursor getOnRowData(int id)
    {
        if (!isDbInitialized || db == null)
        {
            initDb();
        }

        String allColNames[] = new String[dbColumnsArrayList.size() + 1];
        allColNames[0] = "ID";

        for (int i = 0; i < dbColumnsArrayList.size(); i++)
        {
            allColNames[i + 1] = dbColumnsArrayList.get(i).mColumnName;
        }

        Cursor cursor = db.query(TABLE_NAME, allColNames, allColNames[0] + "=?",
                new String[]{String.valueOf(id)}, null, null, null, "1");

        // return cursor.getCount() > 0 ? cursor : null;

        if (cursor.getCount() > 0)
        {
            return cursor;
        }
        else
        {
            return null;
        }
    }

    public Cursor getOneRowData(int columnNumber, String value)
    {
        if (!isDbInitialized || db == null)
        {
            initDb();
        }

        String allColNames[] = new String[dbColumnsArrayList.size() + 1];
        allColNames[0] = "ID";

        for (int i = 0; i < dbColumnsArrayList.size(); i++)
        {
            allColNames[i + 1] = dbColumnsArrayList.get(i).mColumnName;
        }

        Cursor cursor = db.query(TABLE_NAME, allColNames, allColNames[columnNumber] + "=?",
                new String[]{value}, null, null, null, "1");

        if (cursor.getCount() > 0)
        {
            return cursor;
        }
        else
        {
            return null;
        }
    }

    public Cursor getOneRowData(String columnName, String value)
    {
        if (!isDbInitialized || db == null)
        {
            initDb();
        }

        String allColNames[] = new String[dbColumnsArrayList.size() + 1];
        allColNames[0] = "ID";

        for (int i = 0; i < dbColumnsArrayList.size(); i++)
        {
            allColNames[i + 1] = dbColumnsArrayList.get(i).mColumnName;
        }

        Cursor cursor = db.query(TABLE_NAME, allColNames, " " + columnName + " " + "=?",
                new String[]{value}, null, null, null, "1");

        if (cursor.getCount() > 0)
        {
            return cursor;
        }
        else
        {
            return null;
        }
    }

    public boolean matchColumns(String columnsToMatch[], String valuesToMatch[])
    {
        StringBuilder query = new StringBuilder();

        for (int i = 0; i < columnsToMatch.length; i++)
        {
            query.append(columnsToMatch[i]).append(" = ? ");

            if (i != columnsToMatch.length - 1)
            {
                query.append(" AND ");
            }
        }

        Cursor cursor = db.query(TABLE_NAME, columnsToMatch, query.toString(),
                valuesToMatch, null, null, null);

        int count = cursor.getCount();
        cursor.close();

        return count > 0;
    }

    public Db updateData(int columnNumber, String data)
    {
        if (!isDbInitialized || db == null)
        {
            initDb();
        }

        mContentValues.put(dbColumnsArrayList.get(columnNumber - 1).mColumnName, data);
        return this;
    }

    public Db updateData(int columnNumber, int data)
    {
        if (!isDbInitialized || db == null)
        {
            initDb();
        }

        mContentValues.put(dbColumnsArrayList.get(columnNumber - 1).mColumnName, data);
        return this;
    }

    public boolean rowId(int id)
    {
        try
        {
            return db.update(TABLE_NAME, mContentValues, "id = ?",
                    new String[]{String.valueOf(id)}) > 0;
        }
        catch (Exception e)
        {
            Log.e(TAG, "rowId: exception occurred:\n");
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteTable(String tableName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(tableName, null, null) == 1;
    }

    public boolean deleteRow(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "id = ?", new String[]{String.valueOf(id)}) == 1;
    }

    public void deleteAllDataFromTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME);
    }

    public Db setTableName(String tableName)
    {
        this.TABLE_NAME = tableName.replaceAll(" ", "_");
        return this;
    }

    public Db addColumn(DbColumns dbColumns)
    {
        dbColumnsArrayList.add(dbColumns);
        return this;
    }

    public Db doneTableColumn()
    {
        SQL.append("CREATE TABLE IF NOT EXISTS ").append(TABLE_NAME)
           .append(" ( ID INTEGER PRIMARY KEY AUTOINCREMENT, ");

        for (int i = 0; i < dbColumnsArrayList.size(); i++)
        {
            SQL.append(" ").append(dbColumnsArrayList.get(i).mColumnName)
               .append(" ")
               .append(dbColumnsArrayList.get(i).mColumnDataType)
               .append(" ");

            if (i == dbColumnsArrayList.size() - 1)
            {
                SQL.append(" ) ");
            }
            else
            {
                SQL.append(" , ");
            }
        }

        if (!isDbInitialized || db == null)
        {
            initDb();
        }

        db.execSQL(SQL.toString());

        SQL = new StringBuilder();
        mContentValues = new ContentValues();
        dbColumnsArrayList = new ArrayList<>();

        return this;
    }

    public String[] getAllColumns()
    {
        String allColNames[] = new String[dbColumnsArrayList.size() + 1];
        allColNames[0] = "ID";

        for (int i = 0; i < dbColumnsArrayList.size(); i++)
        {
            allColNames[i + 1] = dbColumnsArrayList.get(i).mColumnName;
        }

        return allColNames;
    }

    public static Db init(Context context, String dbName, int version)
    {
        if (!dbName.endsWith(".db"))
        {
            dbName += ".db";
        }

        dbName = dbName.replaceAll(" ", "_");
        return new Db(context, dbName, null, version);
    }

    public static Db init(Context context, String dbName, SQLiteDatabase.CursorFactory factory, int version)
    {
        if (!dbName.endsWith(".db"))
        {
            dbName += ".db";
        }

        dbName = dbName.replaceAll(" ", "_");
        return new Db(context, dbName, factory, version);
    }

    public static Db init(Context context, String dbName, SQLiteDatabase.CursorFactory factory,
                          int version, DatabaseErrorHandler errorHandler)
    {
        if (!dbName.endsWith(".db"))
        {
            dbName += ".db";
        }

        dbName = dbName.replaceAll(" ", "_");
        return new Db(context, dbName, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        this.db = sqLiteDatabase;
        sqLiteDatabase.execSQL(SQL.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    // Saving just in case
    // code below this might once or never be used
    private Context mContext;
    private SQLiteDatabase.CursorFactory mFactory;
    private int mVersion;
    private DatabaseErrorHandler mErrorHandler;

    private Db(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);

        this.mContext = context;
        this.DATABASE_NAME = name;
        this.mFactory = factory;
        this.mVersion = version;
    }

    private Db(Context context, String name, SQLiteDatabase.CursorFactory factory,
               int version, DatabaseErrorHandler errorHandler)
    {
        super(context, name, factory, version);

        this.mContext = context;
        this.DATABASE_NAME = name;
        this.mFactory = factory;
        this.mVersion = version;
        this.mErrorHandler = errorHandler;
    }
}
