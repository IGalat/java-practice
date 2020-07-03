package red.diploma.accounts.service.operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import red.diploma.accounts.model.*;
import red.diploma.accounts.model.operation.ChangeAccountTypeRequest;
import red.diploma.accounts.service.AccountService;
import red.diploma.accounts.service.AccountStatusService;
import red.diploma.accounts.service.AccountTypeService;
import red.diploma.accounts.service.OperationTypeService;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
public class ChangeAccountTypeService {
    private final AccountService accountService;
    private final AccountStatusService accountStatusService;
    private final OperationTypeService operationTypeService;
    private final AccountTypeService accountTypeService;

    @Autowired
    public ChangeAccountTypeService(AccountService accountService, AccountStatusService accountStatusService, OperationTypeService operationTypeService, AccountTypeService accountTypeService) {
        this.accountService = accountService;
        this.accountStatusService = accountStatusService;
        this.operationTypeService = operationTypeService;
        this.accountTypeService = accountTypeService;
    }


    public Operation changeType(ChangeAccountTypeRequest request) {
        Account account = accountService.getOne(request.getAccountId());
        validateForOperation(account);
        AccountType newAccountType = accountTypeService.getByName(request.getType());
        account.setAccountType(newAccountType);
        accountService.update(account);
        return getOperation(account, request);
    }

    private void validateForOperation(Account account) {
        if (account.getAccountStatus().getName() != AccountStatus.Name.OPEN) {
            throw new IllegalArgumentException("Account is not OPEN");
        }
    }

    private Operation getOperation(Account account, ChangeAccountTypeRequest request) {
        return new Operation(operationTypeService.getByName(OperationType.Name.OP_TYPE_ACCOUNT_TYPE_CHANGE)
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
