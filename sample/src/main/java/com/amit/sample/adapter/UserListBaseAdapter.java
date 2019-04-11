package com.amit.sample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.amit.sample.R;
import com.amit.sample.model.User;

import java.util.ArrayList;

/**
 * Created by AMIT JANGID on 11/04/2019.
**/
public class UserListBaseAdapter extends BaseAdapter
{
    private final Context mContext;
    private final ArrayList<User> mUserArrayList;
    
    public UserListBaseAdapter(Context context, ArrayList<User> userArrayList)
    {
        this.mContext = context;
        this.mUserArrayList = userArrayList;
    }
    
    @Override
    public int getCount()
    {
        return mUserArrayList != null ? mUserArrayList.size() : 0;
    }
    
    @Override
    public Object getItem(int position)
    {
        return mUserArrayList.get(position);
    }
    
    @Override
    public long getItemId(int position)
    {
        return position;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        UserListViewHolder holder;
        
        if (convertView == null)
        {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_users_list, parent, false);
            
            holder = new UserListViewHolder();
            holder.tvUserId = convertView.findViewById(R.id.tvUserId);
            holder.tvFullName = convertView.findViewById(R.id.tvFullName);
            
            convertView.setTag(holder);
        }
        else
        {
            holder = (UserListViewHolder) convertView.getTag();
        }
        
        if (mUserArrayList != null && mUserArrayList.size() > 0)
        {
            User user = mUserArrayList.get(position);
            String fullName = user.getFirstName() + "  " + user.getLastName();
            
            holder.tvFullName.setText(fullName);
            holder.tvUserId.setText(String.valueOf(user.get_id()));
        }
        
        return convertView;
    }
    
    private class UserListViewHolder
    {
        private TextView tvUserId, tvFullName;
    }
}
