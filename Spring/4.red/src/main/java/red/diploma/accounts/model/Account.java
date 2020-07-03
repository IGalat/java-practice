package red.diploma.accounts.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import red.diploma.other.model.Bank;
import red.diploma.other.model.Client;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Account extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private AccountType accountType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Bank bank;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private AccountStatus accountStatus;

    @Column(unique = true, nullable = false, updatable = false)
    private Long number;

    @Column(length = 3)
    @Pattern(regexp = "[A-Z]{3}")
    private String currency;

    @Column(unique = true, nullable = false, updatable = false, length = 40)
    @Length(max = 40)
    private String iban;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date opened;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date closed;

    @OneToOne(mappedBy = "account", fetch = FetchType.LAZY, orphanRemoval = true)
    private AccountFinance accountFinance;

    public Account(Client client, AccountType accountType, Bank bank, AccountStatus accountStatus, @Length(min = 10, max = 16) Long number, @Pattern(regexp = "[A-Z]{3}") String currency, @Length(max = 40) String iban, Date opened, Date closed, AccountFinance accountFinance) {
        this.client = client;
        this.accountType = accountType;
        this.bank = bank;
        this.accountStatus = accountStatus;
        this.number = number;
        this.currency = currency;
        this.iban = iban;
        this.opened = opened;
        this.closed = closed;
        this.accountFinance = accountFinance;
    }
}
