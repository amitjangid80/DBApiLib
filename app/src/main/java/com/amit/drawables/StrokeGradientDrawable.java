package com.amit.drawables;

import android.graphics.drawable.GradientDrawable;

public class StrokeGradientDrawable
{
    private int mStrokeWidth, mStrokeColor;
    private GradientDrawable mGradientDrawable;

    public StrokeGradientDrawable(GradientDrawable gradientDrawable)
    {
        this.mGradientDrawable = gradientDrawable;
    }

    public int getStrokeWidth()
    {
        return mStrokeWidth;
    }

    public void setStrokeWidth(int strokeWidth)
    {
        mStrokeWidth = strokeWidth;
        mGradientDrawable.setStroke(strokeWidth, getStrokeColor());
    }

    public int getStrokeColor()
    {
        return mStrokeColor;
    }

    public void setStrokeColor(int strokeColor)
    {
        mStrokeColor = strokeColor;
        mGradientDrawable.setStroke(getStrokeWidth(), strokeColor);
    }

    public GradientDrawable getGradientDrawable()
    {
        return mGradientDrawable;
    }
}
