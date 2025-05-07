package it.uniroma3.siw.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = RatingValidator.class)
@Target({ FIELD })
@Retention(RUNTIME)
public @interface ValidRatingReview {
	
	String message() default "Valutazione non valida";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
