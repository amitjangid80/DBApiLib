package com.amit.validator.validators.text;

import com.amit.validator.annotations.text.ValidateDateFormat;
import com.amit.validator.annotations.text.ValidateEmail;
import com.amit.validator.annotations.text.ValidateExactLength;
import com.amit.validator.annotations.text.ValidateIsNumeric;
import com.amit.validator.annotations.text.ValidateMatchesExpression;
import com.amit.validator.annotations.text.ValidateMaxLength;
import com.amit.validator.annotations.text.ValidateMinLength;
import com.amit.validator.annotations.text.ValidateNotNullOrEmpty;
import com.amit.validator.annotations.text.ValidateUrl;
import com.amit.validator.model.ValidationResult;
import com.amit.validator.model.ValidationStatus;
import com.amit.validator.validators.Validator;

import java.lang.annotation.Annotation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by AMIT JANGID on 19/02/2019.
**/
public class StringValidator implements Validator<String>
{
    private static ValidationResult handleMatchesExpression(final String value, final String expression)
    {
        final ValidationResult notEmptyResult = handleNotNullOrEmpty(value);

        if (notEmptyResult.isValid())
        {
            if (Pattern.compile(expression).matcher(value).matches())
            {
                return ValidationResult.success();
            }
            else
            {
                return new ValidationResult(ValidationStatus.PATTERN_DID_NOT_MATCH);
            }
        }
        else
        {
            return notEmptyResult;
        }
    }

    private static ValidationResult handleUrl(final String value)
    {
        final ValidationResult notEmptyResult = handleNotNullOrEmpty(value);

        if (notEmptyResult.isValid())
        {
            if (TextUrlValidationHelper.isUrl(value))
            {
                return ValidationResult.success();
            }
            else
            {
                return new ValidationResult(ValidationStatus.PATTERN_DID_NOT_MATCH);
            }
        }
        else
        {
            return notEmptyResult;
        }
    }

    private static ValidationResult handleIsNumeric(final String value)
    {
        if (value == null)
        {
            return ValidationResult.nullValue();
        }
        else if (value.isEmpty())
        {
            return new ValidationResult(ValidationStatus.EMPTY_STRING);
        }
        else if (TextNumericValidatorHelper.isNumeric(value))
        {
            return ValidationResult.success();
        }
        else
        {
            return new ValidationResult(ValidationStatus.INVALID_VALUE);
        }
    }

    private static ValidationResult handleMaxLength(final String value, final int length) {
        if (value == null) {
            return ValidationResult.nullValue();
        } else if (value.length() <= length) {
            return ValidationResult.success();
        } else {
            return new ValidationResult(ValidationStatus.VALUE_OUT_OF_RANGE);
        }
    }

    private static ValidationResult handleMinLength(final String value, final int length)
    {
        if (value == null)
        {
            return ValidationResult.nullValue();
        }
        else if (value.length() >= length)
        {
            return ValidationResult.success();
        }
        else
        {
            return new ValidationResult(ValidationStatus.VALUE_OUT_OF_RANGE);
        }
    }

    private static ValidationResult handleExactLength(final String value, final int length)
    {
        if (value == null)
        {
            return ValidationResult.nullValue();
        }
        else if (value.length() == length)
        {
            return ValidationResult.success();
        }
        else
        {
            return new ValidationResult(ValidationStatus.VALUE_OUT_OF_RANGE);
        }
    }

    private static ValidationResult handleEmail(final String value)
    {
        final ValidationResult notEmptyResult = handleNotNullOrEmpty(value);

        if (notEmptyResult.isValid())
        {
            if (TextEmailValidationHelper.isEmail(value))
            {
                return ValidationResult.success();
            }
            else
            {
                return new ValidationResult(ValidationStatus.PATTERN_DID_NOT_MATCH);
            }
        }
        else
        {
            return notEmptyResult;
        }
    }

    private static ValidationResult handleNotNullOrEmpty(final String value)
    {
        if (value == null)
        {
            return ValidationResult.nullValue();
        }
        else if (value.isEmpty())
        {
            return new ValidationResult(ValidationStatus.EMPTY_STRING);
        }
        else
        {
            return ValidationResult.success();
        }
    }

    private static ValidationResult handleIsOfDateFormat(final String text, final String format)
    {
        if (format == null || format.length() == 0)
        {
            return ValidationResult.failure();
        }
        else if (text == null)
        {
            return ValidationResult.failure();
        }
        else
        {
            final Date result;

            try
            {
                result = new SimpleDateFormat(format).parse(text);

                if (result != null)
                {
                    return ValidationResult.success();
                }
                else
                {
                    return ValidationResult.failure();
                }
            }
            catch (final ParseException e)
            {
                return ValidationResult.failure();
            }
        }
    }

    @Override
    public ValidationResult validate(final String value, final Annotation annotation)
    {
        final Class<? extends Annotation> type = annotation.annotationType();

        if (type.equals(ValidateNotNullOrEmpty.class))
        {
            return handleNotNullOrEmpty(value);
        }
        else if (type.equals(ValidateEmail.class))
        {
            return handleEmail(value);
        }
        else if (type.equals(ValidateUrl.class))
        {
            return handleUrl(value);
        }
        else if (type.equals(ValidateMatchesExpression.class))
        {
            return handleMatchesExpression(value, ((ValidateMatchesExpression) annotation).expression());
        }
        else if (type.equals(ValidateExactLength.class))
        {
            return handleExactLength(value, ((ValidateExactLength) annotation).value());
        }
        else if (type.equals(ValidateMinLength.class))
        {
            return handleMinLength(value, ((ValidateMinLength) annotation).value());
        }
        else if (type.equals(ValidateMaxLength.class))
        {
            return handleMaxLength(value, ((ValidateMaxLength) annotation).value());
        }
        else if (type.equals(ValidateIsNumeric.class))
        {
            return handleIsNumeric(value);
        }
        else if (type.equals(ValidateDateFormat.class))
        {
            return handleIsOfDateFormat(value, ((ValidateDateFormat) annotation).value());
        }
        else
        {
            return ValidationResult.failure();
        }
    }
}
