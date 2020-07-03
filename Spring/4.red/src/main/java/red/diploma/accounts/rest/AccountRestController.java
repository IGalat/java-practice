package red.diploma.accounts.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import red.diploma.accounts.model.Account;
import red.diploma.accounts.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("account")
public class AccountRestController {
    private final AccountService accountService;

    @Autowired
    public AccountRestController(AccountService accountService) {
        this.accountService = accountService;
    }


    @GetMapping("id/{id}")
    public Account getOne(@PathVariable Long id) {
        return accountService.getOne(id);
    }

    @GetMapping("client/{clientId}")
    public List<Account> getByClientId(@PathVariable Long clientId) {
        return accountService.getByClientId(clientId);
    }
}
