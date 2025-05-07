package it.uniroma3.siw.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.Year;
/*
 * Implementa la logica della validazione
 */
public class YearValidator implements ConstraintValidator<ValidPublicationYear, Integer> {

    private static final int MIN_YEAR = 1450; 		// Anno minimo valido

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return false; 			// niente valori nulli
        }

        int currentYear = Year.now().getValue();
        return value >= MIN_YEAR && value <= currentYear;
    }
}
