package com.amit.validator.validators.text;

import java.util.regex.Pattern;

/**
 * Created by AMIT JANGID on 19/02/2019.
**/
public class TextNumericValidatorHelper
{
    private static final String EXPRESSION = "-?\\d+([.]\\d+)?";
    private static final Pattern PATTERN = Pattern.compile(EXPRESSION);

    private TextNumericValidatorHelper()
    {
        // do not instantiate
    }

    public static boolean isNumeric(final String input)
    {
        if (input == null)
        {
            return false;
        }
        else
        {
            return PATTERN.matcher(input).matches();
        }
    }
}
