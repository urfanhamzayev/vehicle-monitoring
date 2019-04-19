package com.urfan.customerservice.controller;

import com.urfan.customerservice.model.Customer;
import com.urfan.customerservice.service.CustomerServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CustomerControlerTest {

	private MockMvc mockMvc;

	Customer  customer ;


	@Mock
	private CustomerServiceImpl customerServiceImpl;

	@InjectMocks
	private CustomerController customerController;


	@Before
	public void setup() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();


		customer = new Customer();
		customer.setId(4l);
		customer.setName("name");

		customer.setCountry("Egypt");
		customer.setCity("Giza");
		customer.setState("6th October");
		customer.setAddressLine("ZayedSt");
		customer.setPostalCode("12 961");



	}

	@Test
	public void testGetCustomers() throws Exception {
		List<Customer> customerList = Arrays.asList(customer);

		when(customerServiceImpl.getCustomers()).thenReturn(customerList);

		mockMvc.perform(get("/customer")).andExpect(status().isOk());
	}

	@Test
	public void testGetCustomer() throws Exception {
		customer.setId(Long.parseLong("12"));

		when(customerServiceImpl.getCustomerByID(customer.getId())).thenReturn(customer);

		mockMvc.perform(get("/customer/12")).andExpect(status().isOk());
	}

	@Test
	public void testDeleteCustomer() throws Exception {
        customer.setId(Long.parseLong("12"));
		when(customerServiceImpl.getCustomerByID(customer.getId())).thenReturn(customer);

		mockMvc.perform(delete("/customer/12")).andExpect(status().isOk());
	}

	@Test
	public void testDeleteNonExistingCustomer() throws Exception {

		when(customerServiceImpl.getCustomerByID(null)).thenReturn(null);

		mockMvc.perform(delete("/customer/5")).andExpect(status().isNotFound());

	}


	@Test
	public void testSearchCustomerByName() throws Exception {

		when(customerServiceImpl.searchCustomers(customer.getName())).thenReturn(Arrays.asList(customer));

		mockMvc.perform(get("/customer/search?name=h")).andExpect(status().isOk());
	}


}
