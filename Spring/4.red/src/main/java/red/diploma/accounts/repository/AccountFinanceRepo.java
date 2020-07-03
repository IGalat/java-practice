package red.diploma.accounts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import red.diploma.accounts.model.AccountFinance;

public interface AccountFinanceRepo extends JpaRepository<AccountFinance, Long> {
    AccountFinance getByAccountId(Long accountId);
}
