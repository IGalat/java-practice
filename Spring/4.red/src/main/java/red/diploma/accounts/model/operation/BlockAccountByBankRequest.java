package red.diploma.accounts.model.operation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlockAccountByBankRequest {
    @NotNull
    private Long accountId;

    private String system;
    @NotNull
    private String description;
    private Date executed;
}
