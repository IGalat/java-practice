package red.diploma.other.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import red.diploma.other.model.Client;
import red.diploma.other.service.ClientService;

@RestController
@RequestMapping("client")
public class ClientRestController {
    private final ClientService clientService;

    @Autowired
    public ClientRestController(ClientService clientService) {
        this.clientService = clientService;
    }


    @GetMapping
    public Client getClient(@RequestParam(required = false) Long id
            , @RequestParam(required = false) Long taxId
            , @RequestParam(required = false) String passport) {
        if (id == null && taxId == null && passport == null) {
            throw new IllegalArgumentException("No params to search client with");
        }
        if (id != null)
            return clientService.getOne(id);
        else if (taxId != null)
            return clientService.getByTaxId(taxId);
        else
            return clientService.getByPassport(passport);
    }
}
