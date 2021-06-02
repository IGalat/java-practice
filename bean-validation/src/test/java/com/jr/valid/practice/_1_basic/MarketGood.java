package com.jr.valid.practice._1_basic;

import com.jr.valid.frame.model.ValidationLevel;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.hibernate.validator.constraints.Range;

@MarketGoodCheck(payload = ValidationLevel.MUST.class)
public class MarketGood {
    @PositiveOrZero
    private Integer supply;
    @PositiveOrZero
    private Integer demand;
    @NotNull(payload = ValidationLevel.MUST.class)
    @Range(min = -100, max = 100)
    private Integer saturation;

    public MarketGood() {
    }

    public MarketGood(Integer supply, Integer demand, Integer saturation) {
        this.supply = supply;
        this.demand = demand;
        this.saturation = saturation;
    }

    public Integer getSupply() {
        return supply;
    }

    public void setSupply(Integer supply) {
        this.supply = supply;
    }

    public Integer getDemand() {
        return demand;
    }

    public void setDemand(Integer demand) {
        this.demand = demand;
    }

    public Integer getSaturation() {
        return saturation;
    }

    public void setSaturation(Integer saturation) {
        this.saturation = saturation;
    }
}
