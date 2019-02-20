package com.amit.validator.parser;

import com.amit.validator.annotations.AnnotationCategory;
import com.amit.validator.annotations.Category;
import com.amit.validator.processor.SearchPolicy;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by AMIT JANGID on 19/02/2019.
**/
public class AnnotationParser
{
    /*package*/
    static Annotation getAnnotation(final Object obj, final Class annotationType) throws ReflectiveOperationException
    {
        final List<Field> fields = FieldParser.getAnnotatedFields(obj, SearchPolicy.DEEP);

        for (final Field f : fields)
        {
            if (f.getAnnotations().length != 0 && f.getAnnotation(annotationType) != null)
            {
                return f.getAnnotation(annotationType);
            }
        }

        return null;
    }

    public static AnnotationCategory getCategoryOfAnnotation(final Annotation annotation)
    {
        final Category category = annotation.annotationType().getAnnotation(Category.class);

        if (category != null)
        {
            return category.type();
        }
        else
        {
            return AnnotationCategory.UNUSED;
        }
    }
}
