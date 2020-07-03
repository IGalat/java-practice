package red.diploma.accounts.model;


import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Commission extends Auditable<String> {
    @JsonIgnore
    @Id
    private Long operationTypeId;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn
    @MapsId
    private OperationType operationType;

    @Column
    @NotNull
    @PositiveOrZero
    private Long commissionMinAbsolute;

    @Column
    @NotNull
    @PositiveOrZero
    private Double commissionPercentage;

    public Commission(OperationType operationType, @PositiveOrZero Long commissionMinAbsolute, @PositiveOrZero Double commissionPercentage) {
        this.operationType = operationType;
        this.commissionMinAbsolute = commissionMinAbsolute;
        this.commissionPercentage = commissionPercentage;
    }
}
