package com.amit.imgPickerJava;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.amit.dbapilibrary.R;

import androidx.appcompat.app.AlertDialog;

/**
 * Created by AMIT JANGID on 18/02/2019.
 **/

public class DialogHelper
{
    /**
     * Show Image Provide Picker Dialog. This will streamline the code to pick/capture image
     *
    **/
    void showChooseAppDialog(Context context, final ResultListener<ImageProvider> listener)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View customView = layoutInflater.inflate(R.layout.dialog_choose_app, null);

        LinearLayout lytCameraPick = customView.findViewById(R.id.lytCameraPick);
        LinearLayout lytGalleryPick = customView.findViewById(R.id.lytGalleryPick);

        final AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(R.string.title_choose_image_provider)
                .setView(customView)
                .setNegativeButton(R.string.action_cancel, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                    }
                })
                .show();

        lytCameraPick.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                listener.onResult(ImageProvider.CAMERA);
                dialog.dismiss();
            }
        });

        lytGalleryPick.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                listener.onResult(ImageProvider.GALLERY);
                dialog.dismiss();
            }
        });
    }
}
