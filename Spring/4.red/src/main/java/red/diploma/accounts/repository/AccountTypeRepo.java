package red.diploma.accounts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import red.diploma.accounts.model.AccountType;

public interface AccountTypeRepo extends JpaRepository<AccountType, Long> {
    AccountType getByName(String name);
}
