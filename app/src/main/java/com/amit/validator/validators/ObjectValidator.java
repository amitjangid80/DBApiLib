package com.amit.validator.validators;

import com.amit.validator.annotations.general.IsNull;
import com.amit.validator.annotations.general.NotNull;
import com.amit.validator.model.ValidationResult;
import com.amit.validator.model.ValidationStatus;

import java.lang.annotation.Annotation;

/**
 * Created by AMIT JANGID on 19/02/2019.
**/
public class ObjectValidator implements Validator<Object>
{
    private static ValidationResult handleIsNull(final Object item)
    {
        if (item == null)
        {
            return ValidationResult.success();
        }
        else
        {
            return new ValidationResult(ValidationStatus.INVALID_VALUE);
        }
    }

    private static ValidationResult handleNotNull(final Object item)
    {
        if (item == null)
        {
            return ValidationResult.nullValue();
        }
        else
        {
            return ValidationResult.success();
        }
    }

    @Override
    public ValidationResult validate(final Object item, final Annotation annotation)
    {
        final Class<? extends Annotation> type = annotation.annotationType();

        if (type.equals(NotNull.class))
        {
            return handleNotNull(item);
        }
        else if (type.equals(IsNull.class))
        {
            return handleIsNull(item);
        }
        else
        {
            return ValidationResult.failure();
        }
    }
}
