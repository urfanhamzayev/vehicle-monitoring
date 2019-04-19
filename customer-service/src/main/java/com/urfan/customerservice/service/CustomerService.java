package com.urfan.customerservice.service;

import com.urfan.customerservice.model.Customer;


import java.util.List;

public interface CustomerService  {

    Customer save(Customer customer);

    Customer getCustomerByID(Long customerID);

    void delete(Customer customer);

    List<Customer> searchCustomers(String name);

    List<Customer> getCustomers();
}
