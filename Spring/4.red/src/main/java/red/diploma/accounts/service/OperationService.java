package red.diploma.accounts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import red.diploma.accounts.common.EntityChecker;
import red.diploma.accounts.model.Account;
import red.diploma.accounts.model.Operation;
import red.diploma.accounts.model.operation.*;
import red.diploma.accounts.repository.OperationRepo;
import red.diploma.accounts.service.operation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Service
@Transactional
public class OperationService {
    private final OperationRepo operationRepo;
    private final NewAccountService newAccountService;
    private final OpenAccountService openAccountService;
    private final BlockAccountByClientService blockAccountByClientService;
    private final BlockAccountByBankService blockAccountByBankService;
    private final UnblockAccountService unblockAccountService;
    private final CloseAccountService closeAccountService;
    private final CashToAccountService cashToAccountService;
    private final ChangeAccountTypeService changeAccountTypeService;
    private final ChangeLimitsService changeLimitsService;

    @Autowired
    public OperationService(OperationRepo operationRepo, NewAccountService newAccountService, OpenAccountService openAccountService, BlockAccountByClientService blockAccountByClientService, BlockAccountByBankService blockAccountByBankService, UnblockAccountService unblockAccountService, CloseAccountService closeAccountService, CashToAccountService cashToAccountService, ChangeAccountTypeService changeAccountTypeService, ChangeLimitsService changeLimitsService) {
        this.operationRepo = operationRepo;
        this.newAccountService = newAccountService;
        this.openAccountService = openAccountService;
        this.blockAccountByClientService = blockAccountByClientService;
        this.blockAccountByBankService = blockAccountByBankService;
        this.unblockAccountService = unblockAccountService;
        this.closeAccountService = closeAccountService;
        this.cashToAccountService = cashToAccountService;
        this.changeAccountTypeService = changeAccountTypeService;
        this.changeLimitsService = changeLimitsService;
    }


    public Operation getOne(Long id) {
        return EntityChecker.requireNonNull(operationRepo.getOne(id));
    }

    public List<Operation> getBySubjectAccountId(Long subjectAccountId) {
        return operationRepo.getBySubjectAccountId(subjectAccountId);
    }

    public Operation create(@Valid Operation operation) {
        return operationRepo.save(operation);
    }

    public Account newAccount(@Valid NewAccountRequest newAccountRequest) {
        @Valid Operation operation = newAccountService.newAccount(newAccountRequest);
        return create(operation).getSubjectAccount();
    }

    public Account openAccount(@Valid OpenAccountRequest openAccountRequest) {
        @Valid Operation operation = openAccountService.openAccount(openAccountRequest);
        return create(operation).getSubjectAccount();
    }

    public Account blockAccountByClient(@Valid BlockAccountByClientRequest blockAccountByClientRequest) {
        @Valid Operation operation = blockAccountByClientService.blockAccountByClient(blockAccountByClientRequest);
        return create(operation).getSubjectAccount();
    }

    public Account blockAccountByBank(@Valid BlockAccountByBankRequest blockAccountByBankRequest) {
        @Valid Operation operation = blockAccountByBankService.blockAccountByBank(blockAccountByBankRequest);
        return create(operation).getSubjectAccount();
    }

    public Account unblockAccount(@Valid UnblockAccountRequest unblockAccountRequest) {
        @Valid Operation operation = unblockAccountService.unblockAccount(unblockAccountRequest);
        return create(operation).getSubjectAccount();
    }

    public Account closeAccount(@Valid CloseAccountRequest closeAccountRequest) {
        @Valid Operation operation = closeAccountService.closeAccount(closeAccountRequest);
        return create(operation).getSubjectAccount();
    }

    public Account cashToAccount(@Valid CashToAccountRequest cashToAccountRequest) {
        @Valid Operation operation = cashToAccountService.cashToAccount(cashToAccountRequest);
        return create(operation).getSubjectAccount();
    }

    public Account changeAccountType(@Valid ChangeAccountTypeRequest changeAccountTypeRequest) {
        @Valid Operation operation = changeAccountTypeService.changeType(changeAccountTypeRequest);
        return create(operation).getSubjectAccount();
    }

    public Account changeAccountLimits(@Valid ChangeLimitsRequest changeLimitsRequest) {
        @Valid Operation operation = changeLimitsService.changeLimits(changeLimitsRequest);
        return create(operation).getSubjectAccount();
    }
}
