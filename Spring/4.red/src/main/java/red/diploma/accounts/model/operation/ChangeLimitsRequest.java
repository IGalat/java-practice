package red.diploma.accounts.model.operation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeLimitsRequest {
    @NotNull
    private Long accountId;

    private Long loanLimit;
    private Long overdraftLimit;
    private Long cashLimitDaily;
    private Long cashLimitMonthly;

    private String system;
    @NotNull
    private String description;
    private Date executed;
}
