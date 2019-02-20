package com.amit.validator.annotations.text;

import com.amit.validator.annotations.AnnotationCategory;
import com.amit.validator.annotations.Category;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Created by AMIT JANGID on 19/02/2019.
**/
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Category(type = AnnotationCategory.STRING)
public @interface ValidateExactLength
{
    int value();
}
