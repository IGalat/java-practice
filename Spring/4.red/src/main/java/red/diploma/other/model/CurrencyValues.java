package red.diploma.other.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import red.diploma.accounts.model.Auditable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Entity
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CurrencyValues extends Auditable<String> {
    @Id
    @Column(length = 3)
    @Pattern(regexp = "[A-Z]{3}")
    private String currency;

    @Column(nullable = false)
    @Positive
    private Integer bankSellRatio;

    @Column(nullable = false)
    @Positive
    private Integer bankBuyRatio;

    public CurrencyValues(String currency, @Positive Integer bankSellRatio, @Positive Integer bankBuyRatio) {
        this.currency = currency;
        this.bankSellRatio = bankSellRatio;
        this.bankBuyRatio = bankBuyRatio;
    }
}
