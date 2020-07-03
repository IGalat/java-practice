package red.diploma.accounts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import red.diploma.accounts.common.EntityChecker;
import red.diploma.accounts.model.Commission;
import red.diploma.accounts.model.OperationType;
import red.diploma.accounts.repository.CommissionRepo;
import red.diploma.accounts.repository.OperationTypeRepo;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Service
@Transactional
public class OperationTypeService {
    private final OperationTypeRepo operationTypeRepo;
    private final CommissionRepo commissionRepo;

    @Autowired
    public OperationTypeService(OperationTypeRepo operationTypeRepo, CommissionRepo commissionRepo) {
        this.operationTypeRepo = operationTypeRepo;
        this.commissionRepo = commissionRepo;
    }


    public List<OperationType> findAll() {
        return operationTypeRepo.findAll();
    }

    public OperationType getOne(Long id) {
        return EntityChecker.requireNonNull(operationTypeRepo.getOne(id));
    }

    public OperationType getByName(String name) {
        return getByName(OperationType.Name.valueOf(name.toUpperCase()));
    }

    public OperationType getByName(OperationType.Name name) {
        return EntityChecker.requireNonNull(operationTypeRepo.getByName(name));
    }

    public OperationType update(@Valid OperationType operationType) {
        if (operationType.getName() == null) {
            throw new IllegalArgumentException("Operation name is mandatory for update.");
        }

        OperationType existingOpType = getByName(operationType.getName());
        if (operationType.getId() == null) {
            operationType.setId(existingOpType.getId());
        } else if (existingOpType.getName() != operationType.getName()) {
            throw new IllegalArgumentException("Couldn't update, name and id mismatch. Note: you can't rename operation types.");
        }

        if (operationType.getMonetary() == null) {
            operationType.setMonetary(existingOpType.getMonetary());
        }

        Commission commission = operationType.getCommission();
        if (!operationType.getMonetary()) {
            if (operationType.getMonetaryLimit() != null || commission != null) {
                throw new IllegalArgumentException("Cannot specify monetary limit or commission for non-monetary operation type");
            }
        } else {
            if (operationType.getMonetaryLimit() == null) {
                operationType.setMonetaryLimit(existingOpType.getMonetaryLimit());
            }
            if (operationType.getMonetaryLimit() == null) {
                throw new IllegalArgumentException("For monetary operation monetary limit is mandatory");
            }

            if (commission == null && existingOpType.getCommission() == null) {
                throw new IllegalArgumentException("For monetary operation commission is mandatory");
            }
        }

        if (commission != null) {
            commission.setOperationTypeId(operationType.getId());
            commission.setOperationType(operationType);
            commissionRepo.save(commission);
        }

        return operationTypeRepo.save(operationType);
    }

    public Long getCommissionAmount(OperationType.Name name, Long sumUah) {
        OperationType operationType = getByName(name);
        Commission commission = commissionRepo.getOne(operationType.getId());
        Long absolute = commission.getCommissionMinAbsolute();

        if (commission.getCommissionPercentage() == 0) {
            return absolute;
        }

        Long relative = Math.round(sumUah * (commission.getCommissionPercentage() / 100));
        if (relative < absolute) {
            return absolute;
        } else {
            return relative;
        }
    }
}
