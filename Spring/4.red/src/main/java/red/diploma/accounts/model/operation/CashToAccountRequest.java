package red.diploma.accounts.model.operation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CashToAccountRequest {
    @NotNull
    private Long accountId;
    @NotNull
    private Long amount;

    private String system;
    private String description;
    private Date executed;
}
