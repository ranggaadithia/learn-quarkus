package id.soc.constraints;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = UniqueEmailValidator.class)
@Target({ FIELD })
@Retention(RUNTIME)
public @interface UniqueEmail {

    String message() default "email must be unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
