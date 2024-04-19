package org.university.diplom.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = FunctionValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidFunction {
    String message() default "Invalid type, need double";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
