package red.diploma.accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import red.diploma.accounts.common.Constants;
import red.diploma.accounts.model.Account;
import red.diploma.accounts.model.AccountType;
import red.diploma.accounts.model.operation.NewAccountRequest;
import red.diploma.accounts.model.operation.OpenAccountRequest;
import red.diploma.accounts.service.OperationService;
import red.diploma.other.model.Bank;
import red.diploma.other.model.Client;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class InitAccountsDbFiller {
    private String accTypeStandard0;
    private String accTypeGold1;
    private String accTypePremium2;
    private String accTypeUltra3;
    private Integer bankRed;
    private final static String SYSTEM = "example system";
    private final static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    private final OperationService operationService;

    @Autowired
    public InitAccountsDbFiller(OperationService operationService) {
        this.operationService = operationService;
    }

    public void fill(List<Client> clients, List<AccountType> accountTypes, List<Bank> banks) {
        accTypeStandard0 = accountTypes.get(0).getName();
        accTypeGold1 = accountTypes.get(1).getName();
        accTypePremium2 = accountTypes.get(2).getName();
        accTypeUltra3 = accountTypes.get(3).getName();

        bankRed = banks.get(0).getMfo();

        fillIHalatAccounts(clients.get(0).getId());
        fillLinusAccounts(clients.get(3).getId());
        fillWarrenAccounts(clients.get(5).getId());
    }

    private void fillIHalatAccounts(Long clientId) {
        Account mainUah = operationService.newAccount(new NewAccountRequest(clientId, accTypeUltra3, bankRed, Constants.Currency.UAH, SYSTEM, null, getDate("2007-01-12")));
        mainUah = operationService.openAccount(new OpenAccountRequest(mainUah.getId(), SYSTEM, null, getDate("2007-01-12")));


        Account mainUsd = operationService.newAccount(new NewAccountRequest(clientId, accTypeUltra3, bankRed, Constants.Currency.USD, SYSTEM, null, getDate("2007-05-12")));
        mainUsd = operationService.openAccount(new OpenAccountRequest(mainUsd.getId(), SYSTEM, null, getDate("2007-05-12")));


        Account mainEur = operationService.newAccount(new NewAccountRequest(clientId, accTypeUltra3, bankRed, Constants.Currency.EUR, SYSTEM, null, getDate("2007-05-12")));
        mainEur = operationService.openAccount(new OpenAccountRequest(mainEur.getId(), SYSTEM, null, getDate("2007-05-12")));


        Account closedUah = operationService.newAccount(new NewAccountRequest(clientId, accTypeUltra3, bankRed, Constants.Currency.UAH, SYSTEM, null, getDate("2006-07-19")));
        closedUah = operationService.openAccount(new OpenAccountRequest(closedUah.getId(), SYSTEM, null, getDate("2006-07-19")));
    }

    private void fillLinusAccounts(Long clientId) {
        Account mainUah = operationService.newAccount(new NewAccountRequest(clientId, accTypeUltra3, bankRed, Constants.Currency.UAH, SYSTEM, null, getDate("2012-11-27")));
        mainUah = operationService.openAccount(new OpenAccountRequest(mainUah.getId(), SYSTEM, null, getDate("2012-11-27")));

        Account mainEur = operationService.newAccount(new NewAccountRequest(clientId, accTypeUltra3, bankRed, Constants.Currency.EUR, SYSTEM, null, getDate("2015-08-02")));
        mainEur = operationService.openAccount(new OpenAccountRequest(mainEur.getId(), SYSTEM, null, getDate("2015-08-02")));
    }

    private void fillWarrenAccounts(Long clientId) {
        Account mainUsd = operationService.newAccount(new NewAccountRequest(clientId, accTypeUltra3, bankRed, Constants.Currency.USD, SYSTEM, null, getDate("2009-05-01")));
        mainUsd = operationService.openAccount(new OpenAccountRequest(mainUsd.getId(), SYSTEM, null, getDate("2009-05-01")));

        Account closedUsd = operationService.newAccount(new NewAccountRequest(clientId, accTypeUltra3, bankRed, Constants.Currency.USD, SYSTEM, null, getDate("2018-09-17")));
        closedUsd = operationService.openAccount(new OpenAccountRequest(closedUsd.getId(), SYSTEM, null, getDate("2018-09-17")));
    }

    private Date getDate(String dateString) {
        try {
            return format.parse(dateString);
        } catch (ParseException e) {
            throw new IllegalArgumentException();
        }
    }

}
