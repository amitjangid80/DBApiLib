package com.amit.validator.processor;

import com.amit.validator.ValidationResultsContainer;
import com.amit.validator.annotations.AnnotationCategory;
import com.amit.validator.model.ValidationResult;
import com.amit.validator.model.ValidationStatus;
import com.amit.validator.parser.AnnotationParser;
import com.amit.validator.parser.FieldParser;
import com.amit.validator.validators.ObjectValidator;
import com.amit.validator.validators.Validator;
import com.amit.validator.validators.text.StringValidator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by AMIT JANGID on 19/02/2019.
**/
@SuppressWarnings("WeakerAccess")
public class AnnotatedValidatorProcessor
{
    private static final FailPolicy DEFAULT_FAIL_POLICY = FailPolicy.CONTINUE;
    private static final SearchPolicy DEFAULT_SEARCH_POLICY = SearchPolicy.SHALLOW;

    private final Map<AnnotationCategory, Validator<?>> validatorMap;
    private final FailPolicy failPolicy;
    private final SearchPolicy searchPolicy;

    public AnnotatedValidatorProcessor()
    {
        this(DEFAULT_SEARCH_POLICY, DEFAULT_FAIL_POLICY);
    }

    public AnnotatedValidatorProcessor(final SearchPolicy searchPolicy, final FailPolicy failPolicy)
    {
        this.searchPolicy = searchPolicy;
        this.failPolicy = failPolicy;
        this.validatorMap = new HashMap<>();

        initValidators();
    }

    private static <T> String getHumanName(final T item, final Field field)
    {
        return String.format("%s of type %s in object of type %s",
                field.getName(),
                field.getType().getName(),
                item.getClass().getName());
    }

    private void initValidators()
    {
        validatorMap.put(AnnotationCategory.GENERAL, new ObjectValidator());
        validatorMap.put(AnnotationCategory.STRING, new StringValidator());
        /*validatorMap.put(AnnotationCategory.BYTE, new ByteValidator());
        validatorMap.put(AnnotationCategory.INTEGER, new IntegerValidator());
        validatorMap.put(AnnotationCategory.SHORT, new ShortValidator());
        validatorMap.put(AnnotationCategory.FLOAT, new FloatValidator());
        validatorMap.put(AnnotationCategory.DOUBLE, new DoubleValidator());
        validatorMap.put(AnnotationCategory.BOOLEAN, new BooleanValidator());
        validatorMap.put(AnnotationCategory.COLLECTION, new CollectionValidator());
        validatorMap.put(AnnotationCategory.ARRAY, new ArrayValidator());*/
    }

    public <T> ValidationResultsContainer validate(final T item,
                                                   final SearchPolicy searchPolicy,
                                                   final FailPolicy failPolicy)
    {
        final List<Field> fields;
        final ValidationResultsContainer result = new ValidationResultsContainer();

        try
        {
            fields = FieldParser.getAnnotatedFields(item, searchPolicy);
        }
        catch (final Exception e)
        {
            e.printStackTrace();
            return result;
        }

        for (final Field field : fields)
        {
            System.out.println("Checking field " + getHumanName(item, field));

            try
            {
                field.setAccessible(true);
                final List<ValidationResult> results = validateField(item, field);

                if (!AnnotationProcessorUtils.areAllResultsValid(results))
                {
                    result.put(getHumanName(item, field), AnnotationProcessorUtils.getFailures(results));

                    // end here if the policy is set to fail on first error
                    if (failPolicy.equals(FailPolicy.FAIL_ON_FIRST_ERROR))
                    {
                        return result;
                    }
                }
            }
            catch (final IllegalAccessException e)
            {
                result.put(getHumanName(item, field), ValidationStatus.EXCEPTION);
            }
            finally
            {
                field.setAccessible(false);
            }
        }

        return result;
    }

    public <T> ValidationResultsContainer validate(final T item)
    {
        return this.validate(item, searchPolicy, failPolicy);
    }

    /**
     * Used for <b>testing</b> if all validators have been added to the map.
     * The {@link AnnotationCategory#UNUSED} annotation will return true by default.
     *
     * @param annotationCategory {@link AnnotationCategory} annotation category max
     * @return true if supported and validator is not null for category
    **/
    /*package*/ boolean canSupportAnnotationCategory(final AnnotationCategory annotationCategory)
    {
        //noinspection SimplifiableIfStatement
        if (annotationCategory.equals(AnnotationCategory.UNUSED))
        {
            return true;
        }
        else
        {
            return validatorMap.containsKey(annotationCategory)
                    && validatorMap.get(annotationCategory) != null;
        }
    }

    private <T> List<ValidationResult> validateField(final T item, final Field field) throws IllegalAccessException
    {
        final Annotation[] annotations = field.getDeclaredAnnotations();
        final List<ValidationResult> results = new ArrayList<>();

        if (annotations != null && annotations.length > 0)
        {
            for (final Annotation annotation : annotations)
            {
                final AnnotationCategory category = AnnotationParser.getCategoryOfAnnotation(annotation);
                final ValidationResult result;
                final Object o = field.get(item);

                if (validatorMap.containsKey(category))
                {
                    final Validator validator = validatorMap.get(category);
                    //noinspection unchecked
                    result = validator.validate(o, annotation);
                }
                else
                {
                    result = ValidationResult.success();
                }

                results.add(result);
            }
        }

        return results;
    }
}
