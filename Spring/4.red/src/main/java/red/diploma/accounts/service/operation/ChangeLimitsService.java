package red.diploma.accounts.service.operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import red.diploma.accounts.model.*;
import red.diploma.accounts.model.operation.ChangeLimitsRequest;
import red.diploma.accounts.service.AccountService;
import red.diploma.accounts.service.AccountStatusService;
import red.diploma.accounts.service.OperationTypeService;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
public class ChangeLimitsService {
    private final AccountService accountService;
    private final AccountStatusService accountStatusService;
    private final OperationTypeService operationTypeService;

    @Autowired
    public ChangeLimitsService(AccountService accountService, AccountStatusService accountStatusService, OperationTypeService operationTypeService) {
        this.accountService = accountService;
        this.accountStatusService = accountStatusService;
        this.operationTypeService = operationTypeService;
    }


    public Operation changeLimits(ChangeLimitsRequest request) {
        Account account = accountService.getOne(request.getAccountId());
        validateForOperation(account);

        AccountFinance finance = account.getAccountFinance();
        finance.setOverdraftLimit(request.getOverdraftLimit());
        finance.setLoanLimit(request.getLoanLimit());
        finance.setCashLimitDaily(request.getCashLimitDaily());
        finance.setCashLimitMonthly(request.getCashLimitMonthly());

        accountService.update(account);
        return getOperation(account, request);
    }

    private void validateForOperation(Account account) {
        if (account.getAccountStatus().getName() != AccountStatus.Name.OPEN) {
            throw new IllegalArgumentException("Account is not OPEN");
        }
    }

    private Operation getOperation(Account account, ChangeLimitsRequest request) {
        return new Operation(operationTypeService.getByName(OperationType.Name.OP_TYPE_ACCOUNT_LIMITS_CHANGE)
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
