package com.amit.sample;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.amit.api.WebApiServices;
import com.amit.sample.db.AppDatabase;
import com.amit.sample.model.StyleDetails;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ViewDataActivity extends AppCompatActivity implements StyleDetailsListAdapter.StyleItemClickListener
{
    private static final String TAG = ViewDataActivity.class.getSimpleName();

    private StyleDetailsListAdapter mAdapter;
    private AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);

        RecyclerView rvStyleDetails = findViewById(R.id.rvStyleDetails);
        rvStyleDetails.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new StyleDetailsListAdapter(this, this);
        rvStyleDetails.setAdapter(mAdapter);

        DividerItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        rvStyleDetails.addItemDecoration(decoration);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT)
        {
            @Override
            public boolean onMove(RecyclerView recyclerView,
                                  RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target)
            {
                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction)
            {
                AppExecutors.getInstance().diskIO().execute(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        // for deleting an item on swipe
                        /*int position = viewHolder.getAdapterPosition();
                        List<StyleDetails> details = mAdapter.getStyleDetails();
                        mDb.styleDetailsDao()*/
                    }
                });
            }
        }).attachToRecyclerView(rvStyleDetails);

        // instantiating database
        mDb = AppDatabase.getInstance(getApplicationContext());
        retrieveStyleData();

        WebApiServices apiServices = new WebApiServices(this);
        Log.e(TAG, "onCreate: device id for this mobile is: " + apiServices.getDeviceID());
    }

    // getting data from database
    private void retrieveStyleData()
    {
        try
        {
            final LiveData<List<StyleDetails>> mStyleDetails = mDb.styleDetailsDao().getAllStyleDetails();

            mStyleDetails.observe(this, new Observer<List<StyleDetails>>()
            {
                @Override
                public void onChanged(@Nullable List<StyleDetails> styleDetails)
                {
                    Log.e(TAG, "onChanged: Updating data from live data.");
                    mAdapter.setStyleDetails(styleDetails);
                }
            });
        }
        catch (Exception e)
        {
            Log.e(TAG, "retrieveStyleData: exception while retrieving style data.", e);
        }
    }

    @Override
    public void onItemClick(int itemId)
    {
        Intent intent = new Intent(ViewDataActivity.this, SaveUpdateDataActivity.class);
        intent.putExtra(SaveUpdateDataActivity.STYLE_CODE, itemId);
        startActivity(intent);
    }
}
