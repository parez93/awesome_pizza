package com.parezzan.awesome_pizza.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.UUID;

public class UuidValidator implements ConstraintValidator<ValidUuid, UUID> {
    private final String regex = "^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$";

    @Override
    public void initialize(ValidUuid validUuid) { }

    @Override
    public boolean isValid(UUID uuid, ConstraintValidatorContext cxt) {
        if(uuid == null) {return false;}
        return uuid.toString().matches(this.regex);
    }
}
