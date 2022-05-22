package com.bankapplication.service;

import com.bankapplication.model.Account;
import com.bankapplication.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class AccountService {

    private final CustomerService customerService;
    private List<Account> accountList;

    public AccountService(CustomerService customerService) {
        this.customerService = customerService;
        this.accountList = new ArrayList<>();
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    public Account createAccount(Account account) {
        if (checkCustomerId(account.getCustomerId())) {
            accountList.add(account);
            System.out.println("Account Created " + account.toString());
        }
        return account;
    }

    public void deleteAccountById(String accountId) {
        for (Account temp : accountList) {
            if (temp.getAccountId().equals(accountId)) {
                accountList.remove(temp);
                break;
            }
        }
    }

    public Account getAccount(String accountId) {
        Account account = null;
        for (int i = 0; i < accountList.size(); i++) {
            if (accountId.equals(accountList.get(i).getAccountId())) {
                account = accountList.get(i);
                break;
            }
        }
        return account;
    }

    public Account withdrawMoney(String accountId, Double amount) {
        Account account = getAccount(accountId);
        if (account == null) {
            System.out.println("Could not find any account with this id: " + accountId);
            return null;
        }
        Double balance = account.getBalance();
        if (balance > amount) {
            account.setBalance(balance - amount);
            updateAccount(account, accountId);
        } else {
            System.out.println("Insufficient funds for this accoundId " + accountId + " current balance is " + balance + account.getCurrency());
        }
        return account;
    }

    public Account addMoney(String accountId, Double amount) {
        Account account = getAccount(accountId);
        if (account == null) {
            System.out.println("Could not find any account with this id: " + accountId);
            return null;
        }
        account.setBalance(account.getBalance() + amount);
        updateAccount(account, accountId);
        return account;
    }

    private List<Account> updateAccount(Account account, String accountId) {
        Account oldAccount = new Account();
        for (int i = 0; i < accountList.size(); i++) {
            if (accountId.equals(accountList.get(i).getAccountId())) {
                oldAccount = accountList.get(i);
                accountList.remove(i);
                break;
            }
        }
        oldAccount.setBalance(account.getBalance());

        accountList.add(oldAccount);

        return accountList;
    }

    private boolean checkCustomerId(String customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        if (customer == null) {
            System.out.println("Could not find customer with this id: " + customerId);
            return false;
        } else {
            return true;
        }
    }

}
