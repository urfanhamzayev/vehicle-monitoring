package com.urfan.customerservice.Repository;

import com.urfan.customerservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long  > {

    @Query("SELECT c from Customer c where c.name LIKE  CONCAT( '%', :name, '%') ")
    List<Customer> findCustomerByPartOfName(@Param("name") String name);
}
