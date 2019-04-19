package com.urfan.customerservice.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.urfan.customerservice.Repository.CustomerRepository;
import com.urfan.customerservice.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;



@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback
@SpringBootTest
public class CustomerRepositoryTest {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@PersistenceContext
	private EntityManager em;

	Customer cutomer =null;

	@Before
	public  void setData(){
		cutomer = new Customer();
		cutomer.setName("Urfan");
		cutomer.setCountry("Cyprus");
		cutomer.setCity("Limassol");
		cutomer.setState("Akalma");
		cutomer.setAddressLine("Olimpos");
		cutomer.setPostalCode("555");

	}

	@Test
	public void testInsertCustomer() throws SQLException {

		Customer insertedTestCustomer = customerRepository.save(cutomer);

		String customerName = jdbcTemplate.queryForObject("SELECT NAME FROM CUSTOMER WHERE ID = ? ", String.class,
				insertedTestCustomer.getId());
		assertThat(customerName, equalTo(cutomer.getName()));
	}
	
	
	@Test
	public void testFindCustomerByPartOfName() throws SQLException {

		customerRepository.save(cutomer);
		
		List<Customer> customers = customerRepository.findCustomerByPartOfName("Ur");

		System.out.println("Result : " + customers);
		assertThat(customers.size(), equalTo(1));
	}

}
