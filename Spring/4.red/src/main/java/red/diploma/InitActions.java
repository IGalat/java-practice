package red.diploma;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import red.diploma.accounts.InitAccountsDbFiller;
import red.diploma.accounts.common.AuditorAwareImpl;
import red.diploma.accounts.common.Constants;
import red.diploma.accounts.model.AccountStatus;
import red.diploma.accounts.model.AccountType;
import red.diploma.accounts.model.Commission;
import red.diploma.accounts.model.OperationType;
import red.diploma.accounts.repository.AccountStatusRepo;
import red.diploma.accounts.repository.AccountTypeRepo;
import red.diploma.accounts.repository.CommissionRepo;
import red.diploma.accounts.repository.OperationTypeRepo;
import red.diploma.other.model.Bank;
import red.diploma.other.model.Client;
import red.diploma.other.model.CurrencyValues;
import red.diploma.other.repository.BankRepo;
import red.diploma.other.repository.ClientRepo;
import red.diploma.other.repository.CurrencyValuesRepo;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class InitActions implements CommandLineRunner {
    private final AuditorAwareImpl auditorAwareImpl;
    private final CommissionRepo commissionRepo;
    private final AccountStatusRepo accountStatusRepo;
    private final AccountTypeRepo accountTypeRepo;
    private final OperationTypeRepo operationTypeRepo;
    private final BankRepo bankRepo;
    private final ClientRepo clientRepo;
    private final CurrencyValuesRepo currencyValuesRepo;
    private final InitAccountsDbFiller initAccountsDbFiller;

    @Autowired
    public InitActions(AuditorAwareImpl auditorAwareImpl, AccountStatusRepo accountStatusRepo, AccountTypeRepo accountTypeRepo, OperationTypeRepo operationTypeRepo, BankRepo bankRepo, ClientRepo clientRepo, CurrencyValuesRepo currencyValuesRepo, CommissionRepo commissionRepo, InitAccountsDbFiller initAccountsDbFiller) {
        this.auditorAwareImpl = auditorAwareImpl;
        this.accountStatusRepo = accountStatusRepo;
        this.accountTypeRepo = accountTypeRepo;
        this.operationTypeRepo = operationTypeRepo;
        this.bankRepo = bankRepo;
        this.clientRepo = clientRepo;
        this.currencyValuesRepo = currencyValuesRepo;
        this.commissionRepo = commissionRepo;
        this.initAccountsDbFiller = initAccountsDbFiller;
    }


    @Override
    public void run(String... args) {
        if (isDbEmpty()) {
            auditorAwareImpl.setCurrentAuditor("SYSTEM");
            fillDbWithMandatoryData();
            fillDbWithSampleData();
        }

        auditorAwareImpl.setCurrentAuditor("ExampleUser");
    }

    private boolean isDbEmpty() {
        return (accountTypeRepo.count() == 0);
    }

    private void fillDbWithMandatoryData() {
        fillAccountStatuses();
        fillOperationTypes();
        fillCurrencyValues();
        fillAccountTypes();
    }

    private void fillAccountStatuses() {
        List<@Valid AccountStatus> statusList = new ArrayList<>();
        for (AccountStatus.Name name : AccountStatus.Name.values()) {
            statusList.add(new AccountStatus(name, name.getDescription()));
        }
        accountStatusRepo.saveAll(statusList);
    }

    private void fillOperationTypes() {
        List<@Valid OperationType> operationTypes = new ArrayList<>();
        for (OperationType.Name name : OperationType.Name.values()) {
            if (!name.getMonetary()) {
                operationTypes.add(new OperationType(name, name.getDescription(), false, null, null));
            }
        }
        //add every monetary op type here

        Commission moneyTransferCommission = new Commission(null, 300L, 0.5);
        OperationType moneyTransfer = new OperationType(OperationType.Name.OP_TYPE_MONEY_TRANSFER, OperationType.Name.OP_TYPE_MONEY_TRANSFER.getDescription(),
                true, 5000000L, moneyTransferCommission);
        operationTypes.add(moneyTransfer);


        Commission internalMoneyTransferCommission = new Commission(null, 300L, 0D);
        OperationType internalMoneyTransfer = new OperationType(OperationType.Name.OP_TYPE_INTERNAL_MONEY_TRANSFER, OperationType.Name.OP_TYPE_INTERNAL_MONEY_TRANSFER.getDescription(),
                true, 10000000L, internalMoneyTransferCommission);
        operationTypes.add(internalMoneyTransfer);

        Commission definedRecipientTransferToCommission = new Commission(null, 1000L, 2D);
        OperationType definedRecipientTransferTo = new OperationType(OperationType.Name.OP_TYPE_DEFINED_RECIPIENT_TRANSFER_TO, OperationType.Name.OP_TYPE_DEFINED_RECIPIENT_TRANSFER_TO.getDescription(),
                true, 2000000L, definedRecipientTransferToCommission);
        operationTypes.add(definedRecipientTransferTo);

        Commission definedRecipientTransferFromCommission = new Commission(null, 0L, 0.5);
        OperationType definedRecipientTransferFrom = new OperationType(OperationType.Name.OP_TYPE_DEFINED_RECIPIENT_TRANSFER_FROM, OperationType.Name.OP_TYPE_DEFINED_RECIPIENT_TRANSFER_FROM.getDescription(),
                true, 2000000L, definedRecipientTransferFromCommission);
        operationTypes.add(definedRecipientTransferFrom);

        Commission cacheToAccountCommission = new Commission(null, 0L, 0.5D);
        OperationType cacheToAccount = new OperationType(OperationType.Name.OP_TYPE_CASH_TO_ACCOUNT, OperationType.Name.OP_TYPE_CASH_TO_ACCOUNT.getDescription(),
                true, 5000000L, cacheToAccountCommission);
        operationTypes.add(cacheToAccount);

        Commission cacheFromAccountCommission = new Commission(null, 300L, 1D);
        OperationType cacheFromAccount = new OperationType(OperationType.Name.OP_TYPE_CASH_FROM_ACCOUNT, OperationType.Name.OP_TYPE_CASH_FROM_ACCOUNT.getDescription(),
                true, 2000000L, cacheFromAccountCommission);
        operationTypes.add(cacheFromAccount);

        List<@Valid Commission> commissions = operationTypes.stream()
                .filter(operationType -> operationType.getCommission() != null)
                .peek(operationType -> operationType.getCommission().setOperationType(operationType))
                .map(OperationType::getCommission)
                .collect(Collectors.toList());
        commissionRepo.saveAll(commissions);

        operationTypeRepo.saveAll(operationTypes);
    }

    private void fillCurrencyValues() {
        List<@Valid CurrencyValues> currencyValuesList = new ArrayList<>();
        currencyValuesList.add(new CurrencyValues(Constants.Currency.UAH, 100, 100));
        currencyValuesList.add(new CurrencyValues(Constants.Currency.USD, 2680, 2720));
        currencyValuesList.add(new CurrencyValues(Constants.Currency.EUR, 3005, 3065));
        currencyValuesList.add(new CurrencyValues(Constants.Currency.RUB, 37, 42));

        currencyValuesRepo.saveAll(currencyValuesList);
    }

    private void fillAccountTypes() {
        List<@Valid AccountType> accountTypes = new ArrayList<>();
        accountTypes.add(new AccountType("Стандартний", "", 1000000L, 100000L, 2000000L, 10000000L));
        accountTypes.add(new AccountType("Голд", "", 2000000L, 200000L, 4000000L, 20000000L));
        accountTypes.add(new AccountType("Преміум", "", 5000000L, 500000L, 10000000L, 50000000L));
        accountTypes.add(new AccountType("Преміум Ультра", "", 100000000L, 5000000L, 100000000L, 1000000000L));

        accountTypeRepo.saveAll(accountTypes);
    }


    private void fillDbWithSampleData() {
        fillBanks();
        fillClients();
        fillAccounts();
    }

    private void fillBanks() {
        List<@Valid Bank> banks = new ArrayList<>();
        banks.add(new Bank("Банк Червоного Диплому", "", "Акціонерне товариство \"Банк Червоного Диплому\"", 300000, 12345678));
        banks.add(new Bank("Райффайзен Банк Аваль", "", "Акціонерне товариство \"Райффайзен Банк Аваль\"", 300335, 14305909));
        banks.add(new Bank("Приватбанк", "", "АКЦІОНЕРНЕ ТОВАРИСТВО КОМЕРЦІЙНИЙ БАНК \"ПРИВАТБАНК\"", 305299, 14360570));
        banks.add(new Bank("ПУМБ", "", "\tАКЦІОНЕРНЕ ТОВАРИСТВО \"ПЕРШИЙ УКРАЇНСЬКИЙ МІЖНАРОДНИЙ БАНК\"", 334851, 14282829));
        banks.add(new Bank("Укрсиббанк", "", "АКЦІОНЕРНЕ ТОВАРИСТВО \"УКРСИББАНК\"", 351005, 19807750));

        bankRepo.saveAll(banks);
    }

    private void fillClients() {
        List<@Valid Client> clients = new ArrayList<>();
        clients.add(new Client("Галатюк Ілля Ігорович", 2523424443L, "TT001122"));
        clients.add(new Client("Шепард Джон Ханнович", 1231232134L, "СС332211"));
        clients.add(new Client("Назарбаєв Нурсултан Абішович", 6575665234L, "ЕЕ987123"));
        clients.add(new Client("Лінус Торвальдс Бенедікт", 6667778889L, "TT883344"));
        clients.add(new Client("Гейб Ньюелл Логан", 5345235324L, "ВВ567983"));
        clients.add(new Client("Уоррен Баффет Едвард", 8764393456L, "АА654634"));

        clientRepo.saveAll(clients);
    }

    private void fillAccounts() {
        List<Client> clients = clientRepo.findAll();
        List<AccountType> accountTypes = accountTypeRepo.findAll();
        List<Bank> banks = bankRepo.findAll();

        initAccountsDbFiller.fill(clients, accountTypes, banks);
    }

}
