package com.amit.validator.processor;

import com.amit.validator.model.ValidationResult;
import com.amit.validator.model.ValidationStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AMIT JANGID on 19/02/2019.
**/
public final class AnnotationProcessorUtils
{
    private AnnotationProcessorUtils()
    {
        // NOOP
    }

    public static List<ValidationStatus> getFailures(final List<ValidationResult> results)
    {
        final List<ValidationStatus> statuses = new ArrayList<>();

        for (final ValidationResult result : results)
        {
            // fail if even one of the results is invalid
            if (!result.getStatus().equals(ValidationStatus.SUCCESS))
            {
                statuses.add(result.getStatus());
            }
        }

        return statuses;
    }

    public static boolean areAllResultsValid(final List<ValidationResult> results)
    {
        for (final ValidationResult result : results)
        {
            // fail if even one of the results is invalid
            if (!result.isValid())
            {
                return false;
            }
        }

        return true;
    }
}
