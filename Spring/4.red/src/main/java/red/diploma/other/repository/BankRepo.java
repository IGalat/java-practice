package red.diploma.other.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import red.diploma.other.model.Bank;

public interface BankRepo extends JpaRepository<Bank, Long> {
    Bank getByMfo(Integer mfo);

    Bank getByEdrpou(Integer edrpou);
}
