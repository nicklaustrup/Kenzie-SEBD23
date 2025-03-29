package com.kenzie.dependencyinjection.payroll;

import javax.inject.Inject;
import java.math.BigDecimal;

/**
 * The BankClient which connects to our company's bank
 * to retrieve our payroll account.
 */
public class BankClient {
    private BigDecimal balance = BigDecimal.valueOf(100000);

    /**
     * Private constructor for BankClient so other code can't instantiate us
     */
    @Inject
    public BankClient() { }

    /**
     * Withdraw funds from an account
     * @param amount The amount to withdraw
     * @return the updated balance
     */
    public BigDecimal withdraw(BigDecimal amount) {
        this.balance = balance.subtract(amount);
        System.out.println("Withdrew $" + amount.setScale(2, BigDecimal.ROUND_HALF_UP));
        return balance;
    }
}
