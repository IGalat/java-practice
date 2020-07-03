package red.diploma.accounts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import red.diploma.accounts.model.Operation;

import java.util.List;

public interface OperationRepo extends JpaRepository<Operation, Long> {
    List<Operation> getBySubjectAccountId(Long subjectAccountId);
}
