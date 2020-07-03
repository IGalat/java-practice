package red.diploma.accounts.service.operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import red.diploma.accounts.model.Account;
import red.diploma.accounts.model.AccountStatus;
import red.diploma.accounts.model.Operation;
import red.diploma.accounts.model.OperationType;
import red.diploma.accounts.model.operation.OpenAccountRequest;
import red.diploma.accounts.service.AccountService;
import red.diploma.accounts.service.AccountStatusService;
import red.diploma.accounts.service.OperationService;
import red.diploma.accounts.service.OperationTypeService;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
public class OpenAccountService {
    private final AccountService accountService;
    private final AccountStatusService accountStatusService;
    private final OperationTypeService operationTypeService;

    @Autowired
    public OpenAccountService(AccountService accountService, AccountStatusService accountStatusService, OperationTypeService operationTypeService) {
        this.accountService = accountService;
        this.accountStatusService = accountStatusService;
        this.operationTypeService = operationTypeService;
    }


    public Operation openAccount(OpenAccountRequest request) {
        Account account = accountService.getOne(request.getAccountId());
        validateForOperation(account);
        account = updateForOpening(account, request);
        accountService.update(account);
        return getOperation(account, request);
    }

    private void validateForOperation(Account account) {
        if (account.getAccountStatus().getName() != AccountStatus.Name.NEW) {
            throw new IllegalArgumentException("Account is not NEW");
        }
    }

    private Account updateForOpening(Account account, OpenAccountRequest request) {
        account.setAccountStatus(accountStatusService.getByName(AccountStatus.Name.OPEN));
        account.setOpened(request.getExecuted() != null ? request.getExecuted() : new Date());

        return account;
    }

    private Operation getOperation(Account account, OpenAccountRequest request) {
        return new Operation(operationTypeService.getByName(OperationType.Name.OP_TYPE_OPEN_ACCOUNT)
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
