package red.diploma.accounts.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AccountStatus extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private AccountStatus.Name name;

    @Column
    private String description;

    public AccountStatus(Name name, String description) {
        this.name = name;
        this.description = description;
    }

    public enum Name {
        NEW("New"),
        OPEN("Open"),
        BLOCKED_BY_CLIENT("BLocked on client's request"),
        BLOCKED_BY_BANK("Blocked by bank"),
        CLOSED("Closed");

        private String description;

        Name(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
}
