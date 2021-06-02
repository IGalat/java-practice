package com.jr.valid.practice._1_basic;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MarketGoodSaturationValidator implements ConstraintValidator<MarketGoodCheck, MarketGood> {
    private static final String SATURATION = "saturation";

    @Override
    public boolean isValid(MarketGood marketGood, ConstraintValidatorContext context) {
        boolean valid = true;
        if (marketGood.getSupply() != null && marketGood.getDemand() != null) {
            if (marketGood.getSupply() > marketGood.getDemand() && marketGood.getSaturation() < 0) {
                valid = false;
                context.buildConstraintViolationWithTemplate("Supply is more than demand, saturation shouldn't be negative")
                        .addPropertyNode(SATURATION)
                        .addConstraintViolation();
            }
            if (marketGood.getSupply() < marketGood.getDemand() && marketGood.getSaturation() > 0) {
                valid = false;
                context.buildConstraintViolationWithTemplate("Supply is less than demand, saturation shouldn't be positive")
                        .addPropertyNode(SATURATION)
                        .addConstraintViolation();
            }
        }
        if (!valid)
            context.disableDefaultConstraintViolation();
        return valid;
    }
}
