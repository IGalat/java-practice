package red.diploma.accounts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import red.diploma.accounts.common.EntityChecker;
import red.diploma.accounts.model.Account;
import red.diploma.accounts.model.AccountFinance;
import red.diploma.accounts.model.AccountType;
import red.diploma.accounts.repository.AccountFinanceRepo;
import red.diploma.accounts.repository.AccountRepo;

@Service
@Transactional
public class AccountFinanceService {
    private final AccountFinanceRepo accountFinanceRepo;
    private final AccountRepo accountRepo;

    @Autowired
    public AccountFinanceService(AccountFinanceRepo accountFinanceRepo, AccountRepo accountRepo) {
        this.accountFinanceRepo = accountFinanceRepo;
        this.accountRepo = accountRepo;
    }


    public AccountFinance getByAccountId(Long accountId) {
        return EntityChecker.requireNonNull(accountFinanceRepo.getByAccountId(accountId));
    }

    /**
     * If accountFinance with the accountId exists in db, merge it with input
     *
     * @param one
     * @param accountId
     * @return merged AccountFinance
     */
    public AccountFinance getMergeResult(@NonNull AccountFinance one, Long accountId) {
        if (accountId != null) {
            AccountFinance existing = accountFinanceRepo.getByAccountId(accountId);
            try {
                existing.getCreatedDate();
            } catch (NullPointerException e) {
                return one;
            }

            if (one.getAccount() == null)
                one.setAccount(existing.getAccount());
            if (one.getLoanBlocked() == null) {
                one.setLoanBlocked(existing.getLoanBlocked());
                if (one.getLoanBlocked()) {
                    if (one.getReasonLoanBlocked() == null) {
                        one.setReasonLoanBlocked(existing.getReasonLoanBlocked());
                    }
                } else one.setReasonLoanBlocked(null);
            }
            if (one.getLoanEnabled() == null)
                one.setLoanEnabled(existing.getLoanEnabled());
            if (one.getLoanLimit() == null)
                one.setLoanLimit(existing.getLoanLimit());
            if (one.getOverdraftEnabled() == null)
                one.setOverdraftEnabled(existing.getOverdraftEnabled());
            if (one.getOverdraftLimit() == null)
                one.setOverdraftLimit(existing.getOverdraftLimit());
            if (one.getCashLimitDaily() == null)
                one.setCashLimitDaily(existing.getCashLimitDaily());
            if (one.getCashLimitDailyUsed() == null)
                one.setCashLimitDailyUsed(existing.getCashLimitDailyUsed());
            if (one.getCashLimitMonthly() == null)
                one.setCashLimitMonthly(existing.getCashLimitMonthly());
            if (one.getCashLimitMonthlyUsed() == null)
                one.setCashLimitMonthlyUsed(existing.getCashLimitMonthlyUsed());
        }

        return one;
    }

    public AccountFinance createDefaultAccountFinanceForType(Account account, AccountType accountType) {
        AccountFinance accountFinance = new AccountFinance();
        accountFinance.setAccount(account);
        accountFinance.setLoanBlocked(false);
        accountFinance.setLoanEnabled(true);
        accountFinance.setLoanLimit(accountType.getMaxLoanLimit());
        accountFinance.setOverdraftEnabled(true);
        accountFinance.setOverdraftLimit(accountType.getMaxOverdraftLimit());
        accountFinance.setCashLimitDaily(accountType.getMaxCashLimitDaily());
        accountFinance.setCashLimitDailyUsed(0L);
        accountFinance.setCashLimitMonthly(accountType.getMaxCashLimitMonthly());
        accountFinance.setCashLimitMonthlyUsed(0L);
        accountFinance.setDebitBalance(0L);
        accountFinance.setCreditBalance(0L);
        accountFinance.setBlockedSum(0L);
        return accountFinance;
    }

    public void validateAccountFinance(@NonNull AccountFinance one, @NonNull AccountType accountType) {
        validateAccountFinanceFieldsPresent(one);
        validateAccountStatusLimits(one, accountType);

    }

    private void validateAccountFinanceFieldsPresent(AccountFinance one) {
        String fail = "AccountFinance validation failed, ";

        StringBuilder nullFields = new StringBuilder();
        if (one.getAccount() == null)
            nullFields.append("Account;");
        if (one.getLoanBlocked() == null)
            nullFields.append("LoanBlocked;");
        if (one.getLoanBlocked() && one.getReasonLoanBlocked() == null)
            nullFields.append("ReasonLoanBlocked;");
        if (one.getLoanEnabled() == null)
            nullFields.append("LoanEnabled;");
        if (one.getLoanLimit() == null)
            nullFields.append("LoanLimit;");
        if (one.getOverdraftEnabled() == null)
            nullFields.append("OverdraftEnabled;");
        if (one.getOverdraftLimit() == null)
            nullFields.append("OverdraftLimit;");
        if (one.getCashLimitDaily() == null)
            nullFields.append("CashLimitDaily;");
        if (one.getCashLimitDailyUsed() == null)
            nullFields.append("CashLimitDailyUsed;");
        if (one.getCashLimitMonthly() == null)
            nullFields.append("CashLimitMonthly;");
        if (one.getCashLimitMonthlyUsed() == null)
            nullFields.append("CashLimitMonthlyUsed;");

        if (nullFields.length() > 0)
            throw new IllegalArgumentException(fail + "fields are null: " + nullFields);
    }

    private void validateAccountStatusLimits(AccountFinance one, AccountType accountType) {
        String fail = "AccountFinance validation failed, ";

        StringBuilder limitsErrors = new StringBuilder();
        if (one.getOverdraftLimit() > accountType.getMaxOverdraftLimit())
            limitsErrors.append("Overdraft;");
        if (one.getLoanLimit() > accountType.getMaxLoanLimit())
            limitsErrors.append("Loan;");
        if (one.getCashLimitDaily() > accountType.getMaxCashLimitDaily())
            limitsErrors.append("CashLimitDaily;");
        if (one.getCashLimitMonthly() > accountType.getMaxCashLimitMonthly())
            limitsErrors.append("CashLimitMonthly;");

        if (one.getCashLimitDailyUsed() > one.getCashLimitDaily())
            limitsErrors.append("CashLimitDailyUsed > CashLimitDaily;");
        if (one.getCashLimitMonthlyUsed() > one.getCashLimitMonthly())
            limitsErrors.append("CashLimitMonthlyUsed > CashLimitMonthly");

        if (limitsErrors.length() > 0)
            throw new IllegalArgumentException(fail + "limits are too high for the account type: " + limitsErrors);
    }
}
