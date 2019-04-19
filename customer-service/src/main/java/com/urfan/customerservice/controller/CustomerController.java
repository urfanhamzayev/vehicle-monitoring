package com.urfan.customerservice.controller;

import com.urfan.customerservice.exception.CustomerNotFountException;
import com.urfan.customerservice.model.Customer;
import com.urfan.customerservice.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer")
@Api(value = "Customer API")
@CrossOrigin(origins = "*")
public class CustomerController {


   @Autowired
   CustomerService customerService;



    @ApiOperation(value = "List All Customers.")
    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }


    @ApiOperation(value = "Search a customer by id.")
    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable Long id) {
        Customer customer = customerService.getCustomerByID(id);
        if (customer != null) {
            System.out.println(customer);
            return customer;
        }
        throw new CustomerNotFountException();
    }

    @ApiOperation(value = "Delete a customer by id with the related vehichles.")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable Long id) {
        Customer customer = customerService.getCustomerByID(id);
        if (customer != null) {
            customerService.delete(customer);
        } else {
            throw new CustomerNotFountException();
        }
    }

    @ApiOperation(value = "Add a new customer.")
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void save(@Valid @RequestBody Customer customer) {
        customerService.save(customer);
    }

    @ApiOperation(value = "Search Customers by Name .")
    @GetMapping("/search")
    public List<Customer> searchCustomers(@RequestParam String name,
                                          @RequestParam(required = false, defaultValue = "10") int size) {
        return customerService.searchCustomers(name).stream().collect(Collectors.toList());
    }

}
