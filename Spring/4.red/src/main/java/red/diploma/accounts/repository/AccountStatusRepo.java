package red.diploma.accounts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import red.diploma.accounts.model.AccountStatus;

public interface AccountStatusRepo extends JpaRepository<AccountStatus, Long> {
    AccountStatus getByName(AccountStatus.Name name);
}
