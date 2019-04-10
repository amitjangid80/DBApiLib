package com.amit.sample.adapter;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amit.sample.R;

import androidx.cursoradapter.widget.CursorAdapter;

/**
 * Created by AMIT JANGID on 10/04/2019.
**/
public class UserListAdapter extends CursorAdapter
{
    private final String TAG = UserListAdapter.class.getSimpleName();
    
    public UserListAdapter(Context context, Cursor c)
    {
        super(context, c, 0);
    }
    
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent)
    {
        return LayoutInflater.from(context).inflate(R.layout.item_users_list, parent, false);
    }
    
    @Override
    public void bindView(View view, Context context, Cursor cursor)
    {
        try
        {
            TextView tvUserId, tvFullName;
            
            tvUserId = view.findViewById(R.id.tvUserId);
            tvFullName = view.findViewById(R.id.tvFullName);
            
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
            String firstName = cursor.getString(cursor.getColumnIndexOrThrow("firstName"));
            String lastName = cursor.getString(cursor.getColumnIndexOrThrow("lastName"));
            
            String fullName = firstName + " " + lastName;
            tvFullName.setText(fullName);
            tvUserId.setText(String.valueOf(id));
        }
        catch (Exception e)
        {
            Log.e(TAG, "bindView: exception while biding view:\n");
            e.printStackTrace();
        }
    }
}
