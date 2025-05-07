package it.uniroma3.siw.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RatingValidator implements ConstraintValidator<ValidRatingReview, Integer>{

	private static final int MIN_RATING = 1;
	private static final int MAX_RATING = 5;
	
	
	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		if(value == null)
			return false;
		return value >= MIN_RATING && value <= MAX_RATING;
	}

}
