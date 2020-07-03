package red.diploma.accounts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import red.diploma.accounts.model.Commission;

public interface CommissionRepo extends JpaRepository<Commission, Long> {
}
