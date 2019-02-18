package com.amit.imgPickerJava;

import android.os.Environment;
import android.os.StatFs;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by AMIT JANGID on 18/02/2019.
**/
public class FileUtil
{
    /**
     * @return Return Empty file to store camera image.
    **/
    static File getCameraFile()
    {
        try
        {
            // Create an image file name
            String imageFileName = "IMG_${getTimestamp()}.jpg";

            // Create File Directory Object
            File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "Camera");

            // Create Directory If not exist
            if (!storageDir.exists())
            {
                storageDir.mkdirs();
            }

            // Create File Object
            File file = new File(storageDir, imageFileName);

            // Create empty file
            file.createNewFile();
            return file;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get Current Time in yyyyMMdd HHmmssSSS format
     *
     * 2019/01/30 10:30:20 000
     * E.g. 20190130_103020000
    **/
    private static String getTimeStamp()
    {
        String timeFormat = "ddMMyyyy_HHmmssSS";
        return new SimpleDateFormat(timeFormat, Locale.getDefault()).format(new Date());
    }

    /**
     * Get Free Space size
     * @param file directory object to check free space.
    **/
    long getFreeSpace(File file)
    {
        StatFs stat = new StatFs(file.getPath());
        long availBlocks = stat.getAvailableBlocksLong();
        long blockSize = stat.getBlockSizeLong();
        return availBlocks * blockSize;
    }
}
