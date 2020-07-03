package red.diploma.accounts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import red.diploma.accounts.common.EntityChecker;
import red.diploma.accounts.model.AccountType;
import red.diploma.accounts.repository.AccountTypeRepo;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Service
@Transactional
public class AccountTypeService {
    private final AccountTypeRepo accountTypeRepo;

    @Autowired
    public AccountTypeService(AccountTypeRepo accountTypeRepo) {
        this.accountTypeRepo = accountTypeRepo;
    }

    public List<AccountType> findAll() {
        return accountTypeRepo.findAll();
    }

    public AccountType getOne(Long id) {
        return EntityChecker.requireNonNull(accountTypeRepo.getOne(id));
    }

    public AccountType getByName(String name) {
        return EntityChecker.requireNonNull(accountTypeRepo.getByName(name));
    }

    public AccountType update(@Valid AccountType accountType) {
        if (accountType.getId() == null)
            throw new IllegalArgumentException("Id is mandatory");
        AccountType existingAccountType = accountTypeRepo.getOne(accountType.getId());


        if (accountType.getMaxCashLimitDaily() == null)
            accountType.setMaxCashLimitDaily(existingAccountType.getMaxCashLimitDaily());
        if (accountType.getMaxCashLimitMonthly() == null)
            accountType.setMaxCashLimitMonthly(existingAccountType.getMaxCashLimitMonthly());
        if (accountType.getMaxLoanLimit() == null)
            accountType.setMaxLoanLimit(existingAccountType.getMaxLoanLimit());
        if (accountType.getMaxOverdraftLimit() == null)
            accountType.setMaxOverdraftLimit(existingAccountType.getMaxOverdraftLimit());

        validateAccountTypeLimits(accountType);

        return accountTypeRepo.save(accountType);
    }

    public AccountType create(@Valid AccountType accountType) {
        if(accountType.getId() != null)
            throw new IllegalArgumentException("Id must not be specified");
        validateAccountTypeLimits(accountType);

        return accountTypeRepo.save(accountType);
    }

    private void validateAccountTypeLimits(AccountType type) {
        String error = "";
        if (type.getMaxCashLimitDaily() > type.getMaxCashLimitMonthly())
            error += "Daily max cash limit is more than monthly ";
        if (type.getMaxOverdraftLimit() > type.getMaxLoanLimit())
            error += "Overdraft limit is more than loan limit ";

        if (error.length() > 0)
            throw new IllegalArgumentException(error);
    }
}
