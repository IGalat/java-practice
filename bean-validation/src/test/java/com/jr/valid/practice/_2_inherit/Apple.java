package com.jr.valid.practice._2_inherit;

import com.jr.valid.frame.model.ValidationLevel;
import com.jr.valid.practice._1_basic.MarketGood;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@AppleCheck
public class Apple extends MarketGood {
    public Apple(Integer supply, Integer demand, Integer saturation) {
        this.setSupply(supply);
        this.setDemand(demand);
        this.setSaturation(saturation);
    }

    @NotNull
    @PositiveOrZero(payload = ValidationLevel.MUST.class)
    @Override
    public Integer getSupply() {
        return super.getSupply();
    }
}
