package red.diploma.other.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import red.diploma.other.model.CurrencyValues;
import red.diploma.other.service.CurrencyValuesService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("currency")
public class CurrencyValuesRestController {
    private final CurrencyValuesService currencyValuesService;

    @Autowired
    public CurrencyValuesRestController(CurrencyValuesService currencyValuesService) {
        this.currencyValuesService = currencyValuesService;
    }


    @GetMapping("all")
    public List<CurrencyValues> findAll() {
        return currencyValuesService.findAll();
    }

    @GetMapping("{currency}")
    public CurrencyValues getByCurrency(@PathVariable String currency) {
        return currencyValuesService.getByCurrency(currency);
    }

    @PostMapping
    public void save(@Valid @RequestBody CurrencyValues currencyValues) {
        currencyValuesService.save(currencyValues);
    }
}
