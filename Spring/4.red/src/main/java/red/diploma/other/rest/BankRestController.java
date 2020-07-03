package red.diploma.other.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import red.diploma.other.model.Bank;
import red.diploma.other.service.BankService;

import java.util.List;

@RestController
@RequestMapping("bank")
public class BankRestController {
    private final BankService bankService;

    @Autowired
    public BankRestController(BankService bankService) {
        this.bankService = bankService;
    }


    @GetMapping("all")
    public List<Bank> findAll() {
        return bankService.findAll();
    }

    @GetMapping
    public Bank getOne(@RequestParam(required = false) Long id
            , @RequestParam(required = false) Integer mfo
            , @RequestParam(required = false) Integer edrpou) {
        if (id == null && mfo == null && edrpou == null) {
            throw new IllegalArgumentException("No params to search bank with");
        }
        if (id != null)
            return bankService.getOne(id);
        else if (mfo != null)
            return bankService.getByMfo(mfo);
        else
            return bankService.getByEdrpou(edrpou);
    }
}
