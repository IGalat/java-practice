package red.diploma.accounts.service.operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import red.diploma.accounts.model.Account;
import red.diploma.accounts.model.AccountStatus;
import red.diploma.accounts.model.Operation;
import red.diploma.accounts.model.OperationType;
import red.diploma.accounts.model.operation.BlockAccountByBankRequest;
import red.diploma.accounts.service.AccountService;
import red.diploma.accounts.service.AccountStatusService;
import red.diploma.accounts.service.OperationTypeService;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
public class BlockAccountByBankService {
    private final AccountService accountService;
    private final AccountStatusService accountStatusService;
    private final OperationTypeService operationTypeService;

    @Autowired
    public BlockAccountByBankService(AccountService accountService, AccountStatusService accountStatusService, OperationTypeService operationTypeService) {
        this.accountService = accountService;
        this.accountStatusService = accountStatusService;
        this.operationTypeService = operationTypeService;
    }


    public Operation blockAccountByBank(BlockAccountByBankRequest request) {
        Account account = accountService.getOne(request.getAccountId());
        validateForOperation(account);
        account = updateForBlocking(account);
        accountService.update(account);
        return getOperation(account, request);
    }

    private void validateForOperation(Account account) {
        AccountStatus.Name status = account.getAccountStatus().getName();
        if (status != AccountStatus.Name.OPEN && status != AccountStatus.Name.BLOCKED_BY_CLIENT) {
            throw new IllegalArgumentException("Account is not OPEN or BLOCKED_BY_CLIENT");
        }
    }

    private Account updateForBlocking(Account account) {
        account.setAccountStatus(accountStatusService.getByName(AccountStatus.Name.BLOCKED_BY_BANK));
        return account;
    }

    private Operation getOperation(Account account, BlockAccountByBankRequest request) {
        return new Operation(operationTypeService.getByName(OperationType.Name.OP_TYPE_BLOCK_ACCOUNT_BY_BANK)
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
