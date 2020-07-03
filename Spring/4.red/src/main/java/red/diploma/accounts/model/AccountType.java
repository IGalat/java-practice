package red.diploma.accounts.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AccountType extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    @Length(max = 50)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @PositiveOrZero
    private Long maxLoanLimit;

    @Column(nullable = false)
    @PositiveOrZero
    private Long maxOverdraftLimit;

    @Column(nullable = false)
    @PositiveOrZero
    private Long maxCashLimitDaily;

    @Column(nullable = false)
    @PositiveOrZero
    private Long maxCashLimitMonthly;

    public AccountType(String name, String description, @PositiveOrZero Long maxLoanLimit, @PositiveOrZero Long maxOverdraftLimit, @PositiveOrZero Long maxCashLimitDaily, @PositiveOrZero Long maxCashLimitMonthly) {
        this.name = name;
        this.description = description;
        this.maxLoanLimit = maxLoanLimit;
        this.maxOverdraftLimit = maxOverdraftLimit;
        this.maxCashLimitDaily = maxCashLimitDaily;
        this.maxCashLimitMonthly = maxCashLimitMonthly;
    }
}
