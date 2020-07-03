package red.diploma.other.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import red.diploma.other.model.CurrencyValues;

public interface CurrencyValuesRepo extends JpaRepository<CurrencyValues, String> {
    CurrencyValues getByCurrency(String currency);
}
