package red.diploma.accounts.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import red.diploma.accounts.model.Account;
import red.diploma.accounts.model.Operation;
import red.diploma.accounts.model.operation.*;
import red.diploma.accounts.service.OperationService;

import java.util.List;

@RestController
@RequestMapping("op")
public class OperationRestController {
    private final OperationService operationService;

    @Autowired
    public OperationRestController(OperationService operationService) {
        this.operationService = operationService;
    }


    @RequestMapping("id/{id}")
    public Operation getOne(@PathVariable Long id) {
        return operationService.getOne(id);
    }

    @RequestMapping("subject/account/id/{id}")
    public List<Operation> getBySubjectAccountId(@PathVariable("id") Long subjectAccountId) {
        return operationService.getBySubjectAccountId(subjectAccountId);
    }

    @PostMapping("account")
    public Account newAccount(@RequestBody NewAccountRequest newAccountRequest) {
        return operationService.newAccount(newAccountRequest);
    }

    @PutMapping("account/open")
    public Account openAccount(@RequestBody OpenAccountRequest openAccountRequest) {
        return operationService.openAccount(openAccountRequest);
    }

    @PutMapping("account/block-by-client")
    public Account blockByClient(@RequestBody BlockAccountByClientRequest blockAccountByClientRequest) {
        return operationService.blockAccountByClient(blockAccountByClientRequest);
    }

    @PutMapping("account/block-by-bank")
    public Account blockByBank(@RequestBody BlockAccountByBankRequest blockAccountByBankRequest) {
        return operationService.blockAccountByBank(blockAccountByBankRequest);
    }

    @PutMapping("account/unblock")
    public Account unblock(@RequestBody UnblockAccountRequest unblockAccountRequest) {
        return operationService.unblockAccount(unblockAccountRequest);
    }

    @PutMapping("account/close")
    public Account close(@RequestBody CloseAccountRequest closeAccountRequest) {
        return operationService.closeAccount(closeAccountRequest);
    }

    @PutMapping("account/cash-to-account")
    public Account cashToAccount(@RequestBody CashToAccountRequest cashToAccountRequest) {
        return operationService.cashToAccount(cashToAccountRequest);
    }

    @PutMapping("account/change-type")
    public Account changeType(@RequestBody ChangeAccountTypeRequest changeAccountTypeRequest) {
        return operationService.changeAccountType(changeAccountTypeRequest);
    }

    @PutMapping("account/change-limits")
    public Account changeLimits(@RequestBody ChangeLimitsRequest changeLimitsRequest) {
        return operationService.changeAccountLimits(changeLimitsRequest);
    }
}
