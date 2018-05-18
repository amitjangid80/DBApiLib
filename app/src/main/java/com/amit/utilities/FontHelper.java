package com.amit.utilities;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Hashtable;

public class FontHelper
{
    private static final String TAG = FontHelper.class.getSimpleName();
    private static Hashtable<String, Typeface> fontCache = new Hashtable<>();

    /**
     * apply font method
     *
     * Apply specified font for all views (including nested ones) in the specified root view.
    **/
    public static void applyFont(final Context context, final View root, final String fontPath)
    {
        try
        {
            if (root instanceof ViewGroup)
            {
                ViewGroup viewGroup = (ViewGroup) root;
                int childCount = viewGroup.getChildCount();

                for (int i = 0; i < childCount; i++)
                {
                    applyFont(context, viewGroup.getChildAt(i), fontPath);
                }
            }
            else if (root instanceof TextView)
            {
                ((TextView) root).setTypeface(Typeface.createFromAsset(context.getAssets(), fontPath));
            }
        }
        catch (Exception ex)
        {
            Log.e(TAG, "Exception in FontHelper class.\n");
            ex.printStackTrace();
        }
    }

    public static Typeface getTypeface(Context context,String fontName)
    {
        Typeface tf = fontCache.get(fontName);

        if (tf == null)
        {
            try
            {
                tf = Typeface.createFromAsset(context.getAssets(), fontName);
            }
            catch (Exception e)
            {
                Log.e(TAG, "getTypeface: exception while getting typeface.");
                e.printStackTrace();
                return null;
            }

            fontCache.put(fontName, tf);
        }

        return tf;
    }
}
