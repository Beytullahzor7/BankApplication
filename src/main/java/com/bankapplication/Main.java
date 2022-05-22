package com.bankapplication;

import com.bankapplication.model.Account;
import com.bankapplication.model.City;
import com.bankapplication.model.Currency;
import com.bankapplication.model.Customer;
import com.bankapplication.service.AccountService;
import com.bankapplication.service.CustomerService;

public class Main {
    public static void main(String[] args) {

        CustomerService customerService = new CustomerService();
        AccountService accountService = new AccountService(customerService);

        Customer customer1 = new Customer("1", "Beytullah", "Atakum", City.SAMSUN, "beytullahzor7@gmail.com", "0543", 1998);
        Customer customer2 = new Customer("2", "Mehmet", "Cankaya", City.ANKARA, "mehmet06@gmail.com", "0544", 1999);

        customerService.createCustomer(customer1);
        customerService.createCustomer(customer2);

        Account account1 = new Account("1", "1", 2022, Currency.USD, 500.0);
        Account account2 = new Account("2", "2", 2022, Currency.TRY, 300.0);

        accountService.createAccount(account1);
        accountService.createAccount(account2);

        System.out.println(accountService.withdrawMoney("1", 750.0));

    }
}
