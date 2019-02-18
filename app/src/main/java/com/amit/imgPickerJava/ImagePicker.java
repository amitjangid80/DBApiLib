package com.amit.imgPickerJava;

import androidx.annotation.NonNull;

/**
 * Created by AMIT JANGID on 18/02/2019.
**/
public class ImagePicker
{
    public static final int RESULT_ERROR = 64;
    public static final int REQUEST_CODE = 2404;

    @NonNull
    public static final String EXTRA_IMAGE_PROVIDER = "extra.image_provider";

    @NonNull
    public static final String EXTRA_IMAGE_MAX_SIZE = "extra.image_max_size";

    @NonNull
    public static final String EXTRA_CROP_X = "extra.crop_x";

    @NonNull
    public static final String EXTRA_CROP_Y = "extra.crop_y";

    @NonNull
    public static final String EXTRA_MAX_WIDTH = "extra.max_width";

    @NonNull
    public static final String EXTRA_MAX_HEIGHT = "extra.max_height";

    @NonNull
    public static final String EXTRA_ERROR = "extra.error";

    @NonNull
    public static final String EXTRA_FILE_PATH = "extra.file_path";
}
