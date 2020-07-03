package red.diploma.accounts.service.operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import red.diploma.accounts.model.Account;
import red.diploma.accounts.model.AccountType;
import red.diploma.accounts.model.Operation;
import red.diploma.accounts.model.OperationType;
import red.diploma.accounts.model.operation.NewAccountRequest;
import red.diploma.accounts.service.AccountService;
import red.diploma.accounts.service.AccountStatusService;
import red.diploma.accounts.service.OperationService;
import red.diploma.accounts.service.OperationTypeService;
import red.diploma.other.model.Bank;
import red.diploma.other.model.Client;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Transactional
public class NewAccountService {
    private final AccountService accountService;
    private final OperationTypeService operationTypeService;

    @Autowired
    public NewAccountService(AccountService accountService, OperationTypeService operationTypeService) {
        this.accountService = accountService;
        this.operationTypeService = operationTypeService;
    }


    public Operation newAccount(@Valid NewAccountRequest request) {
        @Valid Account account = fillStubsForNew(request);
        account = accountService.create(account);
        return getOperation(account, request);
    }

    private Account fillStubsForNew(NewAccountRequest request) {
        Account account = new Account();

        final Client client = new Client();
        client.setId(request.getClientId());
        account.setClient(client);

        final AccountType accountType = new AccountType();
        accountType.setName(request.getAccountType());
        account.setAccountType(accountType);

        final Bank bank = new Bank();
        bank.setMfo(request.getBankMfo());
        account.setBank(bank);

        account.setNumber(ThreadLocalRandom.current().nextLong(1000000000000000L, 10000000000000000L));

        account.setCurrency(request.getCurrency());

        account.setIban("UW" + account.getNumber() + "LHHH");

        return account;
    }

    private Operation getOperation(Account account, NewAccountRequest request) {
        return new Operation(operationTypeService.getByName(OperationType.Name.OP_TYPE_NEW_ACCOUNT)
                , account
                , null
                , null
                , null
                , null
                , null
                , null
                , null
                , null
                , null
                , null
                , null
                , null
                , request.getSystem()
                , request.getDescription()
                , request.getExecuted() != null ? request.getExecuted() : new Date()
        );
    }
}
