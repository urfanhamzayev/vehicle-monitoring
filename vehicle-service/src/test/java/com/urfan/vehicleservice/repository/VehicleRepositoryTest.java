package com.urfan.vehicleservice.repository;

import com.urfan.vehicleservice.Repository.VehicleRepository;
import com.urfan.vehicleservice.model.OnlineStatus;
import com.urfan.vehicleservice.model.Vehicle;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback
@SpringBootTest
public class VehicleRepositoryTest {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PersistenceContext
    private EntityManager em;

    Vehicle myTestVehicle =null;

    @Before
    public void setData(){
         myTestVehicle = new Vehicle();
        myTestVehicle.setVehicleID("12678111113451111");
        myTestVehicle.setRegisterationNumber("123456");
        myTestVehicle.setCustomerID(5l);
        myTestVehicle.setStatus(OnlineStatus.OFFLNE);
    }

    @Test
    public void testInsertVehicle() throws SQLException {


        Vehicle insertedTestVehicle = vehicleRepository.save(myTestVehicle);

        String vehicleID = jdbcTemplate.queryForObject("SELECT VEHICLE_ID FROM VEHICLE WHERE ID = ? ", String.class,
                insertedTestVehicle.getId());
        assertThat(vehicleID, equalTo(myTestVehicle.getVehicleID()));
    }

    @Test
    public void testFindByVehicleID() throws SQLException  {



        vehicleRepository.save(myTestVehicle);

        Vehicle returnedVehicle = vehicleRepository.findByRegisterationNumber(myTestVehicle.getRegisterationNumber()).get();

        assertThat(myTestVehicle.getVehicleID(), equalTo(returnedVehicle.getVehicleID()));
    }
}
