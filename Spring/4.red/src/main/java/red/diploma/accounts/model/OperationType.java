package red.diploma.accounts.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OperationType extends Auditable<String> {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true, length = 100)
    private OperationType.Name name;

    @Column
    private String description;

    @Column(nullable = false)
    private Boolean monetary;

    @Column
    @PositiveOrZero
    private Long monetaryLimit;

    @OneToOne(mappedBy = "operationType", fetch = FetchType.LAZY)
    private Commission commission;

    public OperationType(OperationType.Name name, String description, Boolean monetary, Long monetaryLimit, Commission commission) {
        this.name = name;
        this.description = description;
        this.monetary = monetary;
        this.monetaryLimit = monetaryLimit;
        this.commission = commission;
    }

    public enum Name {
        OP_TYPE_NEW_ACCOUNT("Creation of account", false),
        OP_TYPE_OPEN_ACCOUNT("Opening of account", false),
        OP_TYPE_BLOCK_ACCOUNT_BY_CLIENT("Account blocking by client", false),
        OP_TYPE_BLOCK_ACCOUNT_BY_BANK("Account blocking by bank", false),
        OP_TYPE_UNBLOCK_ACCOUNT("Account unblocking", false),
        OP_TYPE_CLOSE_ACCOUNT("Closing of account", false),
        OP_TYPE_MONEY_TRANSFER("Money transfer to another person's account", true),
        OP_TYPE_INTERNAL_MONEY_TRANSFER("Money transfer between accounts of one person", true),
        OP_TYPE_DEFINED_RECIPIENT_TRANSFER_TO("Money transfer from Red Diploma Bank account to another bank's account", true),
        OP_TYPE_DEFINED_RECIPIENT_TRANSFER_FROM("Money transfer from another bank's account to Red Diploma Bank account", true),
        OP_TYPE_ACCOUNT_LIMITS_CHANGE("Change in account's limits", false),
        OP_TYPE_ACCOUNT_TYPE_CHANGE("Change of account type", false),
        OP_TYPE_CASH_TO_ACCOUNT("User replenished account debit with cache", true),
        OP_TYPE_CASH_FROM_ACCOUNT("User withdrawn account debit to cache", true),
        ;

        private String description;

        private Boolean isMonetary;

        Name(String description, Boolean isMonetary) {
            this.description = description;
            this.isMonetary = isMonetary;
        }

        public String getDescription() {
            return description;
        }

        public Boolean getMonetary() {
            return isMonetary;
        }
    }
}
