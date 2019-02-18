package com.amit.imgPickerJava;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import com.amit.dbapilibrary.R;

import java.io.File;

import androidx.core.content.FileProvider;

/**
 * Created by AMIT JANGID on 18/02/2019.
**/
public class IntentUtil
{
    /**
     * @return Intent Gallery Intent
    **/
    static Intent getGalleryIntent()
    {
        Intent intent;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        }
        else
        {
            intent = new Intent(Intent.ACTION_PICK);
        }

        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        return intent;
    }

    /**
     * @return Intent Camera Intent
    **/
    static Intent getCameraIntent(Context context, File file)
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        {
            String authority = context.getPackageName() + context.getString(R.string.image_picker_provider_authority_suffix);
            Uri photoUri = FileProvider.getUriForFile(context, authority, file);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
        }
        else
        {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        }

        return intent;
    }
}
