package red.diploma.accounts.service.operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import red.diploma.accounts.model.Account;
import red.diploma.accounts.model.AccountStatus;
import red.diploma.accounts.model.Operation;
import red.diploma.accounts.model.OperationType;
import red.diploma.accounts.model.operation.UnblockAccountRequest;
import red.diploma.accounts.service.AccountService;
import red.diploma.accounts.service.AccountStatusService;
import red.diploma.accounts.service.OperationTypeService;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
public class UnblockAccountService {
    private final AccountService accountService;
    private final AccountStatusService accountStatusService;
    private final OperationTypeService operationTypeService;

    @Autowired
    public UnblockAccountService(AccountService accountService, AccountStatusService accountStatusService, OperationTypeService operationTypeService) {
        this.accountService = accountService;
        this.accountStatusService = accountStatusService;
        this.operationTypeService = operationTypeService;
    }

    public Operation unblockAccount(UnblockAccountRequest request) {
        Account account = accountService.getOne(request.getAccountId());
        validateForOperation(account);
        account = updateForUnblocking(account);
        accountService.update(account);
        return getOperation(account, request);
    }

    private void validateForOperation(Account account) {
        AccountStatus.Name status = account.getAccountStatus().getName();
        if (status != AccountStatus.Name.BLOCKED_BY_CLIENT && status != AccountStatus.Name.BLOCKED_BY_BANK) {
            throw new IllegalArgumentException("Account is not BLOCKED_BY_CLIENT or BLOCKED_BY_BANK");
        }
    }

    private Account updateForUnblocking(Account account) {
        account.setAccountStatus(accountStatusService.getByName(AccountStatus.Name.OPEN));
        return account;
    }

    private Operation getOperation(Account account, UnblockAccountRequest request) {
        return new Operation(operationTypeService.getByName(OperationType.Name.OP_TYPE_UNBLOCK_ACCOUNT)
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
