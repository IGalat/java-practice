package red.diploma.accounts.service.operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import red.diploma.accounts.model.Account;
import red.diploma.accounts.model.AccountStatus;
import red.diploma.accounts.model.Operation;
import red.diploma.accounts.model.OperationType;
import red.diploma.accounts.model.operation.CashToAccountRequest;
import red.diploma.accounts.service.AccountService;
import red.diploma.accounts.service.AccountStatusService;
import red.diploma.accounts.service.OperationTypeService;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
public class CashToAccountService {
    private final AccountService accountService;
    private final AccountStatusService accountStatusService;
    private final OperationTypeService operationTypeService;

    @Autowired
    public CashToAccountService(AccountService accountService, AccountStatusService accountStatusService, OperationTypeService operationTypeService) {
        this.accountService = accountService;
        this.accountStatusService = accountStatusService;
        this.operationTypeService = operationTypeService;
    }


    public Operation cashToAccount(CashToAccountRequest request) {
        Account account = accountService.getOne(request.getAccountId());
        validateForOperation(account);

        Long commission = operationTypeService.getCommissionAmount(OperationType.Name.OP_TYPE_CASH_TO_ACCOUNT, request.getAmount());
        Long sumAfterCommission = request.getAmount() - commission;
        Long resultBalance = account.getAccountFinance().getDebitBalance() + sumAfterCommission;
        account.getAccountFinance().setDebitBalance(resultBalance);

        accountService.update(account);
        return getOperation(account, request, commission, sumAfterCommission);
    }

    private void validateForOperation(Account account) {
        AccountStatus.Name status = account.getAccountStatus().getName();
        if (status != AccountStatus.Name.OPEN) {
            throw new IllegalArgumentException("Account is not OPEN");
        }
    }

    private Operation getOperation(Account account, CashToAccountRequest request, Long commission, Long sumAfterCommission) {
        return new Operation(operationTypeService.getByName(OperationType.Name.OP_TYPE_CASH_TO_ACCOUNT)
                , account
                , null
                , null
                , null
                , null
                , null
                , null
                , null
                , account.getCurrency()
                , request.getAmount()
                , commission
                , sumAfterCommission
                , null
                , request.getSystem()
                , request.getDescription()
                , request.getExecuted() != null ? request.getExecuted() : new Date()
        );
    }
}
