package red.diploma.accounts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import red.diploma.accounts.model.OperationType;

public interface OperationTypeRepo extends JpaRepository<OperationType, Long> {
    OperationType getByName(OperationType.Name name);
}
