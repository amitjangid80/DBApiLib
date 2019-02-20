package com.amit.validator.validators;

import com.amit.validator.model.ValidationResult;

import java.lang.annotation.Annotation;

/**
 * Created by AMIT JANGID on 19/02/2019.
**/
public interface Validator<T>
{
    ValidationResult validate(T item, Annotation annotation);
}
