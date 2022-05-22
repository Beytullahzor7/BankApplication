package com.bankapplication.service;

import com.bankapplication.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerService {

    private List<Customer> customerList;

    public CustomerService() {
        this.customerList = new ArrayList<>();
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public Customer createCustomer(Customer customer) {
        customerList.add(customer);
        System.out.println("Customer Created " + customer.toString());
        return customer;
    }

    public Customer updateCustomer(Customer customer, String customerId) {
        Customer oldCustomer = new Customer();
        for (Customer temp : customerList) {
            if (customerId.equals(temp.getId())) {
                oldCustomer.setId(temp.getId());
                customerList.remove(temp);
                break;
            }
        }
        oldCustomer.setName(customer.getName());
        oldCustomer.setAddress(customer.getAddress());
        oldCustomer.setCity(customer.getCity());
        oldCustomer.setEmail(customer.getEmail());
        oldCustomer.setPhone(customer.getPhone());
        oldCustomer.setBirthDate(customer.getBirthDate());

        customerList.add(oldCustomer);
        return oldCustomer;
    }

    public void deleteCustomer(String customerId) {
        for (Customer temp : customerList) {
            if (customerId.equals(temp.getId())) {
                customerList.remove(temp);
                break;
            }
        }
    }

    public Customer getCustomerById(String customerId) {
        Customer customer = null;
        for (int i = 0; i < customerList.size(); i++) {
            if (customerId.equals(customerList.get(i).getId())) {
                customer = customerList.get(i);
                break;
            }
        }
        return customer;
    }
}
