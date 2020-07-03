package red.diploma.accounts.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import red.diploma.accounts.model.AccountType;
import red.diploma.accounts.service.AccountTypeService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("account/type")
public class AccountTypeRestController {
    private final AccountTypeService accountTypeService;

    @Autowired
    public AccountTypeRestController(AccountTypeService accountTypeService) {
        this.accountTypeService = accountTypeService;
    }


    @GetMapping("all")
    public List<AccountType> findAll() {
        return accountTypeService.findAll();
    }

    @GetMapping("id/{id}")
    public AccountType getOne(@PathVariable Long id) {
        return accountTypeService.getOne(id);
    }

    @GetMapping("{name}")
    public AccountType getByName(@PathVariable String name) {
        return accountTypeService.getByName(name);
    }

    @PutMapping
    public void update(@Valid @RequestBody AccountType accountType) {
        accountTypeService.update(accountType);
    }

    @PostMapping
    public void create(@Valid @RequestBody AccountType accountType) {
        accountTypeService.create(accountType);
    }
}
