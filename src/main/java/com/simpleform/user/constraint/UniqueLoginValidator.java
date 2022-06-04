package com.simpleform.user.constraint;

import com.simpleform.user.model.UserModel;
import com.simpleform.user.repository.UserRepository;
import org.hibernate.criterion.Example;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueLoginValidator implements ConstraintValidator<UniqueLoginConstraint, String> {
    private UserRepository repository;

    public UniqueLoginValidator(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void initialize(UniqueLoginConstraint constraint) {}

    @Override
    public boolean isValid(String field, ConstraintValidatorContext context) {
        if (field == null || field.isEmpty()) {
            return true;
        }

        UserModel user = this.repository.findFirstByLogin(field).orElse(null);
        if (user == null) {
            return true;
        }

        return false;
    }
}
