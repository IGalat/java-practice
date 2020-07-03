package red.diploma.accounts.model.operation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewAccountRequest {
    @NotNull
    private Long clientId;
    @NotNull
    private String accountType;
    @NotNull
    private Integer bankMfo;
    @NotNull
    private String currency;

    private String system;
    private String description;
    private Date executed;
}
