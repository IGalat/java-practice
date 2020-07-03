package red.diploma.accounts.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import red.diploma.accounts.model.AccountStatus;
import red.diploma.accounts.service.AccountStatusService;

import java.util.List;

@RestController
@RequestMapping("account/status")
public class AccountStatusRestController {
    private final AccountStatusService accountStatusService;

    @Autowired
    public AccountStatusRestController(AccountStatusService accountStatusService) {
        this.accountStatusService = accountStatusService;
    }


    @GetMapping("all")
    public List<AccountStatus> findAll() {
        return accountStatusService.findAll();
    }

    @GetMapping("id/{id}")
    public AccountStatus getOne(@PathVariable Long id) {
        return accountStatusService.getOne(id);
    }

    @GetMapping("{name}")
    public AccountStatus getByName(@PathVariable String name) {
        return accountStatusService.getByName(name);
    }
}
