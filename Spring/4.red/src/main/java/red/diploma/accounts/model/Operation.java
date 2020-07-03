package red.diploma.accounts.model;


import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import red.diploma.accounts.common.Constants;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Operation extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private OperationType operationType;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("subjectAccountId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Account subjectAccount;

    @Column
    private String subjectFullName;

    @Column
    private Long subjectTaxId;

    @Column(length = 12)
    @Pattern(regexp = Constants.PASSPORT_PATTERN)
    private String subjectPassport;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("objectAccountId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Account objectAccount;

    @Column
    private String objectFullName;

    @Column
    private Long objectTaxId;

    @Column(length = 12)
    @Pattern(regexp = Constants.PASSPORT_PATTERN)
    private String objectPassport;

    @Column(length = 3)
    @Pattern(regexp = "[A-Z]{3}")
    private String currency;

    @Column
    @PositiveOrZero
    private Long operationAmount;

    @Column
    @PositiveOrZero
    private Long commission;

    @Column
    private Long subjectBalanceChange;

    @Column
    private Long objectBalanceChange;

    @Column
    private String system;

    @Column
    private String description;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date executed;

    public Operation(OperationType operationType, Account subjectAccount, String subjectFullName, Long subjectTaxId, @Pattern(regexp = Constants.PASSPORT_PATTERN) String subjectPassport, Account objectAccount, String objectFullName, Long objectTaxId, @Pattern(regexp = Constants.PASSPORT_PATTERN) String objectPassport, @Pattern(regexp = "[A-Z]{3}") String currency, @PositiveOrZero Long operationAmount, @PositiveOrZero Long commission, Long subjectBalanceChange, Long objectBalanceChange, String system, String description, Date executed) {
        this.operationType = operationType;
        this.subjectAccount = subjectAccount;
        this.subjectFullName = subjectFullName;
        this.subjectTaxId = subjectTaxId;
        this.subjectPassport = subjectPassport;
        this.objectAccount = objectAccount;
        this.objectFullName = objectFullName;
        this.objectTaxId = objectTaxId;
        this.objectPassport = objectPassport;
        this.currency = currency;
        this.operationAmount = operationAmount;
        this.commission = commission;
        this.subjectBalanceChange = subjectBalanceChange;
        this.objectBalanceChange = objectBalanceChange;
        this.system = system;
        this.description = description;
        this.executed = executed;
    }
}
