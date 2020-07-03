package red.diploma.accounts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import red.diploma.accounts.common.EntityChecker;
import red.diploma.accounts.model.AccountStatus;
import red.diploma.accounts.repository.AccountStatusRepo;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AccountStatusService {
    private final AccountStatusRepo accountStatusRepo;

    @Autowired
    public AccountStatusService(AccountStatusRepo accountStatusRepo) {
        this.accountStatusRepo = accountStatusRepo;
    }

    public List<AccountStatus> findAll() {
        return accountStatusRepo.findAll();
    }


    public AccountStatus getOne(Long id) {
        return EntityChecker.requireNonNull(accountStatusRepo.getOne(id));
    }

    public AccountStatus getByName(AccountStatus.Name name) {
        return EntityChecker.requireNonNull(accountStatusRepo.getByName(name));
    }

    public AccountStatus getByName(String name) {
        return getByName(AccountStatus.Name.valueOf(name));
    }
}
