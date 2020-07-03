package red.diploma.other.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import red.diploma.accounts.common.EntityChecker;
import red.diploma.other.model.Bank;
import red.diploma.other.repository.BankRepo;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BankService {
    private final BankRepo bankRepo;

    @Autowired
    public BankService(BankRepo bankRepo) {
        this.bankRepo = bankRepo;
    }


    public List<Bank> findAll() {
        return bankRepo.findAll();
    }

    public Bank getOne(Long id) {
        return EntityChecker.requireNonNull(bankRepo.getOne(id));
    }

    public Bank getByMfo(Integer mfo) {
        return EntityChecker.requireNonNull(bankRepo.getByMfo(mfo));
    }

    public Bank getByEdrpou(Integer edrpou) {
        return EntityChecker.requireNonNull(bankRepo.getByEdrpou(edrpou));
    }
}
