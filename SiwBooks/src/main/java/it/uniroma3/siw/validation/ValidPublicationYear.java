package it.uniroma3.siw.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = YearValidator.class)
@Target({ FIELD })
@Retention(RUNTIME)
public @interface ValidPublicationYear {

    String message() default "Anno di pubblicazione non valido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
