package com.kenzie.bank;

import com.kenzie.bank.exceptions.InsufficientFundsException;
import com.kenzie.bank.exceptions.InvalidInputException;

import java.math.BigDecimal;

/**
 * This class represents a checking bank account, which includes methods that
 * allow money to be deposited or withdrawn from the account.
 */
public class CheckingAccount implements BankAccount {

    /**
     * Identification number associated with the account.
     */
    private String accountId;

    /**
     * Amount of money in the account.
     */
    private BigDecimal balance;

    /*
     * Validator helper class for validating input.
     */
    private Validator validator = new Validator();

    public CheckingAccount(String accountId, BigDecimal balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    /**
     * Deposit amount to account.
     *
     * @param amount of money to deposit
     * @return value of account after the deposit
     */
    @Override
    public BigDecimal deposit(BigDecimal amount) {
         validator.validate(amount);
         balance = balance.add(amount);
        return getBalance();
    }

    /**
     * Withdraw amount from account.
     *
     * @param amount of money to withdraw
     * @return value of account after the withdraw
     * @throws InsufficientFundsException if account does not have enough funds to withdraw amount
     */
    @Override
    public BigDecimal withdraw(BigDecimal amount) throws InsufficientFundsException{
            validator.validate(amount);
            if (getBalance().intValue() < amount.intValue()){
                throw new InsufficientFundsException("Insufficient funds");
            } else {
                balance = balance.subtract(amount);
            }
            return getBalance();
    }

    @Override
    public BigDecimal getBalance() {
        return this.balance;    }
}