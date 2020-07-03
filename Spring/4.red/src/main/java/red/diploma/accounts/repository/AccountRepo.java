package red.diploma.accounts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import red.diploma.accounts.model.Account;

import java.util.List;

public interface AccountRepo extends JpaRepository<Account, Long> {
    List<Account> getByClientId(Long clientId);
}
