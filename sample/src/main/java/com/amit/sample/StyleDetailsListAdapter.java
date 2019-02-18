package com.amit.sample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amit.sample.model.StyleDetails;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Amit Jangid on 24,May,2018
 **/
public class StyleDetailsListAdapter extends RecyclerView.Adapter<StyleDetailsListAdapter.StyleHolder>
{
    private static final String TAG = StyleDetailsListAdapter.class.getSimpleName();

    // Constant for date format
    private static final String DATE_FORMAT = "dd/MM/yyyy";

    private StyleItemClickListener mItemClickListener;
    private Context mContext;

    private List<StyleDetails> mStyleDetails;
    private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());

    public StyleDetailsListAdapter(Context mContext, StyleItemClickListener mItemClickListener)
    {
        this.mContext = mContext;
        this.mItemClickListener = mItemClickListener;
    }

    @NonNull
    @Override
    public StyleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_style_details, parent, false);

        return new StyleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StyleHolder holder, int position)
    {
        StyleDetails styleDetail = mStyleDetails.get(position);

        holder.tvStyleNo.setText(styleDetail.getStyleNo());
        holder.tvStyleCode.setText(String.valueOf(styleDetail.getStyleCode()));

        holder.tvStyImgFY.setText(styleDetail.getStyImgFY());
        holder.tvStyImgHY.setText(styleDetail.getStyImgHY());
        holder.tvStyImgFW.setText(styleDetail.getStyImgFW());
        holder.tvStyImgHW.setText(styleDetail.getStyImgHY());

        holder.tvCreatedOn.setText(dateFormat.format(styleDetail.getCreatedOn()));
        holder.tvUpdatedOn.setText(dateFormat.format(styleDetail.getUpdatedOn()));
    }

    @Override
    public int getItemCount()
    {
        if (mStyleDetails == null)
        {
            return 0;
        }

        return mStyleDetails.size();
    }

    public List<StyleDetails> getStyleDetails()
    {
        return mStyleDetails;
    }

    public void setStyleDetails(List<StyleDetails> styleDetails)
    {
        mStyleDetails = styleDetails;
        notifyDataSetChanged();
    }

    public interface StyleItemClickListener
    {
        void onItemClick(int itemId);
    }

    class StyleHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView tvStyleNo, tvStyleCode, tvStyImgFY, tvStyImgHY,
                         tvStyImgFW, tvStyImgHW, tvCreatedOn, tvUpdatedOn;

        StyleHolder(View itemView)
        {
            super(itemView);

            tvStyleNo = itemView.findViewById(R.id.tvStyleNo);
            tvStyleCode = itemView.findViewById(R.id.tvStyleCode);
            tvStyImgFY = itemView.findViewById(R.id.tvStyImgFY);
            tvStyImgHY = itemView.findViewById(R.id.tvStyImgHY);
            tvStyImgFW = itemView.findViewById(R.id.tvStyImgFW);
            tvStyImgHW = itemView.findViewById(R.id.tvStyImgHW);
            tvCreatedOn = itemView.findViewById(R.id.tvCreatedOn);
            tvUpdatedOn = itemView.findViewById(R.id.tvUpdatedOn);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v)
        {
            int code = mStyleDetails.get(getAdapterPosition()).getCode();
            mItemClickListener.onItemClick(code);
        }
    }
}
