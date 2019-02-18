package com.amit.imgPickerJava;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.ExifInterface;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by AMIT JANGID on 18/02/2019.
**/
public class ImageUtil
{
    File compressImage(File imageFile, float reqWidth, float reqHeight,
                       Bitmap.CompressFormat compressFormat, int quality, String destinationPath)
            throws IOException
    {
        FileOutputStream fileOutputStream = null;
        File file = new File(destinationPath).getParentFile();

        if (!file.exists())
        {
            file.mkdirs();
        }

        try
        {
            fileOutputStream = new FileOutputStream(destinationPath);

            // write the compressed bitmap at the destination specified by destinationPath.
            decodeSampledBitmapFromFile(imageFile, reqWidth, reqHeight).compress(compressFormat, quality, fileOutputStream);
        }
        finally
        {
            if (fileOutputStream != null)
            {
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        }

        return new File(destinationPath);
    }

    private Bitmap decodeSampledBitmapFromFile(File imageFile, float reqWidth, float reqHeight)
        throws IOException
    {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath(), options);

        int actualWidth = options.outWidth;
        int actualHeight = options.outHeight;

        float maxRatio = reqWidth / reqHeight;
        float imageRatio = actualWidth / actualHeight;

        if (actualHeight > reqHeight || actualWidth > reqWidth)
        {
            if (imageRatio < maxRatio)
            {
                imageRatio = reqHeight / actualHeight;
                actualHeight = Integer.parseInt(String.valueOf(reqHeight));
                actualWidth = Integer.parseInt(String.valueOf((imageRatio * actualWidth)));
            }
            else if (imageRatio > maxRatio)
            {
                imageRatio = reqHeight / actualHeight;
                actualWidth = Integer.parseInt(String.valueOf(reqWidth));
                actualHeight = Integer.parseInt(String.valueOf(imageRatio * actualHeight));
            }
            else
            {
                actualWidth = Integer.parseInt(String.valueOf(reqWidth));
                actualHeight = Integer.parseInt(String.valueOf(reqHeight));
            }
        }

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, actualWidth, actualHeight);
        options.inJustDecodeBounds = false;
        options.inDither = false;
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inTempStorage = new byte[16 * 1024];

        try
        {
            bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath(), options);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        Bitmap scaledBitmap = null;

        try
        {
            scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight, Bitmap.Config.ARGB_8888);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        int ratioX = actualWidth / options.outWidth;
        int ratioY = actualHeight / options.outHeight;

        float middleX = actualWidth / 2.0f;
        float middleY = actualHeight / 2.0f;

        Matrix scaledMatrix = new Matrix();
        scaledMatrix.setScale(ratioX, ratioY, middleX, middleY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaledMatrix);
        canvas.drawBitmap(bitmap, middleX - bitmap.getWidth() / 2, middleY - bitmap.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));
        bitmap.recycle();

        ExifInterface exif;

        try
        {
            Matrix matrix = new Matrix();
            exif = new ExifInterface(imageFile.getAbsolutePath());
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 0);

            if (orientation == 6)
            {
                matrix.postRotate(90f);
            }
            else if (orientation == 3)
            {
                matrix.postRotate(180f);
            }
            else if (orientation == 8)
            {
                matrix.postRotate(270f);
            }

            scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix, true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return scaledBitmap;
    }

    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight)
    {
        int inSampleSize = 1;
        int width = options.outWidth;
        int height = options.outHeight;

        if (height > reqHeight || width > reqWidth)
        {
            inSampleSize *= 2;

            int halfWidth = width / 2;
            int halfHeight = height / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth)
            {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}
