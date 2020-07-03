package red.diploma.other.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import red.diploma.accounts.common.EntityChecker;
import red.diploma.other.model.CurrencyValues;
import red.diploma.other.repository.CurrencyValuesRepo;

import javax.validation.Valid;
import java.util.List;

@Service
@Transactional
public class CurrencyValuesService {
    private final CurrencyValuesRepo currencyValuesRepo;

    @Autowired
    public CurrencyValuesService(CurrencyValuesRepo currencyValuesRepo) {
        this.currencyValuesRepo = currencyValuesRepo;
    }


    public List<CurrencyValues> findAll() {
        return currencyValuesRepo.findAll();
    }

    public CurrencyValues getByCurrency(String currency) {
        return EntityChecker.requireNonNull(currencyValuesRepo.getByCurrency(currency.toUpperCase()));
    }

    public CurrencyValues save(@Valid CurrencyValues currencyValues) {
        return currencyValuesRepo.save(currencyValues);
    }
}
