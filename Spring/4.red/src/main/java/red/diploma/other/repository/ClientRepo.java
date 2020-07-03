package red.diploma.other.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import red.diploma.other.model.Client;

public interface ClientRepo extends JpaRepository<Client, Long> {

    Client getByTaxId(Long taxId);

    Client getByPassport(String passport);
}
