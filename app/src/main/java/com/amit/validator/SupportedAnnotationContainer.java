package com.amit.validator;

import com.amit.validator.utils.ReflectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by AMIT JANGID on 19/02/2019.
**/
public class SupportedAnnotationContainer
{
    private static final String PACKAGE_NAME = "com.amit.validator.annotations";
    private static final List<Class> SUPPORTED_CLASSES = new ArrayList<>();

    private SupportedAnnotationContainer()
    {

    }

    /*package*/
    static boolean isSupported(final Class<?> type)
    {
        return getSupportedClasses().contains(type);
    }

    public static List<Class> getSupportedClasses()
    {
        if (SUPPORTED_CLASSES.isEmpty())
        {
            final Class[] supportedArray;

            try
            {
                supportedArray = ReflectionUtils.getClasses(PACKAGE_NAME);
                SUPPORTED_CLASSES.addAll(Arrays.asList(supportedArray));
            }
            catch (ClassNotFoundException | IOException e)
            {
                e.printStackTrace();
            }
        }

        return SUPPORTED_CLASSES;
    }
}
