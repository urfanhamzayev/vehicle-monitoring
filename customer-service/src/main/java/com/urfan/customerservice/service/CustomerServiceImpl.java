package com.urfan.customerservice.service;

import com.urfan.customerservice.Repository.CustomerRepository;
import com.urfan.customerservice.exception.CustomerNotFountException;
import com.urfan.customerservice.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    /**
     *  The customer repository management.
     */
    @Autowired
     CustomerRepository customerRepository;



    @Override
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }


    /**
     * Get the customer by the customer database generated identifier.
     */


    @Override
    public Customer getCustomerByID(Long customerID) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerID);
        return optionalCustomer.orElseThrow(CustomerNotFountException::new);
    }

    /**
     * Add or Update customers.
     */
    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }


    /**
     * Delete Customer
     */
    @Override
    @Transactional
    public void delete(Customer customer) {
        customerRepository.delete(customer);
    }

    @Override
    public List<Customer> searchCustomers(String name) {
        return customerRepository.findCustomerByPartOfName(name);
    }

}
