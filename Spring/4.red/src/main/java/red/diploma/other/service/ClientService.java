package red.diploma.other.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import red.diploma.accounts.common.EntityChecker;
import red.diploma.other.model.Client;
import red.diploma.other.repository.ClientRepo;

import javax.transaction.Transactional;

@Service
@Transactional
public class ClientService {
    private final ClientRepo clientRepo;

    @Autowired
    public ClientService(ClientRepo clientRepo) {
        this.clientRepo = clientRepo;
    }


    public Client getOne(Long id) {
        return EntityChecker.requireNonNull(clientRepo.getOne(id));
    }

    public Client getByTaxId(Long taxId) {
        return EntityChecker.requireNonNull(clientRepo.getByTaxId(taxId));
    }

    public Client getByPassport(String passport) {
        return EntityChecker.requireNonNull(clientRepo.getByPassport(passport));
    }
}
