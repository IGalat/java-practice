package red.diploma.accounts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AccountFinance extends Auditable<String> {
    @Id
    private Long accountId;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn
    @MapsId
    private Account account;

    @Column(nullable = false)
    private Boolean loanBlocked;

    @Column
    private String reasonLoanBlocked;

    @Column(nullable = false)
    private Boolean loanEnabled;

    @Column(nullable = false)
    private Long loanLimit;

    @Column(nullable = false)
    private Boolean overdraftEnabled;

    @Column(nullable = false)
    private Long overdraftLimit;

    @Column(nullable = false)
    private Long cashLimitDaily;

    @Column(nullable = false)
    private Long cashLimitDailyUsed;

    @Column(nullable = false)
    private Long cashLimitMonthly;

    @Column(nullable = false)
    private Long cashLimitMonthlyUsed;

    @Column(nullable = false)
    private Long debitBalance;

    @Column(nullable = false)
    private Long creditBalance;

    @Column(nullable = false)
    private Long blockedSum;
}
