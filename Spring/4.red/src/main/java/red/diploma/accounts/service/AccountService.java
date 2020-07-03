package red.diploma.accounts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import red.diploma.accounts.common.EntityChecker;
import red.diploma.accounts.model.Account;
import red.diploma.accounts.model.AccountFinance;
import red.diploma.accounts.model.AccountStatus;
import red.diploma.accounts.model.AccountType;
import red.diploma.accounts.repository.AccountFinanceRepo;
import red.diploma.accounts.repository.AccountRepo;
import red.diploma.other.model.Bank;
import red.diploma.other.model.Client;
import red.diploma.other.service.BankService;
import red.diploma.other.service.ClientService;
import red.diploma.other.service.CurrencyValuesService;

import javax.validation.Valid;
import java.util.List;

@Service
@Transactional
public class AccountService {
    private final AccountRepo accountRepo;
    private final AccountFinanceRepo accountFinanceRepo;
    private final AccountFinanceService accountFinanceService;
    private final AccountTypeService accountTypeService;
    private final AccountStatusService accountStatusService;
    private final ClientService clientService;
    private final BankService bankService;
    private final CurrencyValuesService currencyValuesService;

    @Autowired
    public AccountService(AccountRepo accountRepo, AccountFinanceRepo accountFinanceRepo, AccountFinanceService accountFinanceService, AccountTypeService accountTypeService, ClientService clientService, BankService bankService, CurrencyValuesService currencyValuesService, AccountStatusService accountStatusService) {
        this.accountRepo = accountRepo;
        this.accountFinanceRepo = accountFinanceRepo;
        this.accountFinanceService = accountFinanceService;
        this.accountTypeService = accountTypeService;
        this.clientService = clientService;
        this.bankService = bankService;
        this.currencyValuesService = currencyValuesService;
        this.accountStatusService = accountStatusService;
    }


    public Account getOne(Long id) {
        return EntityChecker.requireNonNull(accountRepo.getOne(id));
    }

    public List<Account> getByClientId(Long clientId) {
        return accountRepo.getByClientId(clientId);
    }

    public Account create(@Valid Account account) {
        if (account.getAccountStatus() != null && account.getAccountStatus().getName() != AccountStatus.Name.NEW) {
            throw new IllegalArgumentException("Status incorrect for the new account. Don't specify it.");
        }
        account.setAccountStatus(accountStatusService.getByName(AccountStatus.Name.NEW));
        account = fillStubsAndValidateAccount(account);
        Account result = accountRepo.save(account);
        accountFinanceRepo.save(account.getAccountFinance());
        return result;
    }

    public Account update(@Valid Account account) {
        if (account.getId() == null) {
            throw new IllegalArgumentException("Cannot update account without id");
        }
        account = fillStubsAndValidateAccount(account);
        Account result = accountRepo.save(account);
        accountFinanceRepo.save(account.getAccountFinance());
        return result;
    }

    /**
     * Fill stubs based on:
     * client - id
     * account type - name
     * bank - mfo
     * account status - id
     * account finance should be empty, it is created according to account type.
     */
    private Account fillStubsAndValidateAccount(@NonNull Account account) {
        Client client = clientService.getOne(account.getClient().getId());
        account.setClient(client);

        AccountType accountType = accountTypeService.getByName(account.getAccountType().getName());
        account.setAccountType(accountType);

        AccountFinance accountFinance = account.getAccountFinance();
        if (accountFinance == null) {
            accountFinance = accountFinanceService.createDefaultAccountFinanceForType(account, accountType);
        } else {
            accountFinance = accountFinanceService.getMergeResult(accountFinance, account.getId());
            accountFinanceService.validateAccountFinance(accountFinance, accountType);
        }
        account.setAccountFinance(accountFinance);

        Bank bank = bankService.getByMfo(account.getBank().getMfo());
        account.setBank(bank);

        AccountStatus accountStatus = accountStatusService.getOne(account.getAccountStatus().getId());
        account.setAccountStatus(accountStatus);

        currencyValuesService.getByCurrency(account.getCurrency());

        if (account.getIban() == null || account.getIban().length() < 15)
            throw new IllegalArgumentException("IBAN incorrect or absent");

        return account;
    }
}
