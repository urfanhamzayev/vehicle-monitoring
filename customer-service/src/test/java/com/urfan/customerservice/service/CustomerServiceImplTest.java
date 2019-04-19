package com.urfan.customerservice.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.urfan.customerservice.Repository.CustomerRepository;
import com.urfan.customerservice.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;



@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {

	@Mock
	private CustomerRepository customerRepository;

	@InjectMocks
	private CustomerServiceImpl customerService;


	Customer customer =null;

	@Before
	public  void setData(){
		customer = new Customer();
		customer.setId(Long.parseLong("11"));
		customer.setName("Urfan");
		customer.setCountry("Cyprus");
		customer.setCity("Limassol");
		customer.setState("Akalma");
		customer.setAddressLine("Olimpos");
		customer.setPostalCode("555");

	}
	
	@Test
	public void testGetCustomerByID() {
		when(customerRepository.findById(1l)).thenReturn(Optional.of(customer));

		Customer retrunedCustomer = customerService.getCustomerByID(1l);
		assertEquals(customer, retrunedCustomer);
	}

	@Test
	public void testSave() {
		when(customerRepository.save(customer)).thenReturn(customer);

		customerService.save(customer);
	}

	@Test
	public void testGetCustomers() {
		when(customerRepository.findAll())
				.thenReturn(Arrays.asList(customer, customer));

		List<Customer> retrunedCustomers = customerService.getCustomers();
		assertEquals(2, retrunedCustomers.size());
	}

	

}
