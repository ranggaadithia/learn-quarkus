package id.soc.constraints;

import id.soc.entity.User;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return User.find("email", value).firstResult() == null;
    }

}
