package com.urfan.vehicleservice.controller;

import com.urfan.vehicleservice.model.OnlineStatus;
import com.urfan.vehicleservice.model.PingSignal;
import com.urfan.vehicleservice.model.Vehicle;
import com.urfan.vehicleservice.service.VehicleServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class VehicleControlerTest {

    Vehicle vehicle, vehicle1;
    private MockMvc mockMvc;

    @Mock
    private VehicleServiceImpl vehicleServiceImpl;

    @InjectMocks
    private VehicleController vehicleController;


    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(vehicleController).build();

        vehicle = new Vehicle();
        vehicle.setId(5l);
        vehicle.setVehicleID("12345678901234567");
        vehicle.setLastPingDate(ZonedDateTime.now());

        vehicle1 = new Vehicle();
        vehicle1.setId(4l);
        vehicle1.setCustomerID(5l);
        vehicle1.setVehicleID("12345678901234567");
        vehicle1.setRegisterationNumber("123456");
        vehicle1.setStatus(OnlineStatus.OFFLNE);

    }

    @Test
    public void testGetVehicles() throws Exception {

        List<Vehicle> vehicleList = Arrays.asList(vehicle);

        when(vehicleServiceImpl.getVehicles()).thenReturn(vehicleList);

        this.mockMvc.perform(get("/vehicle")).andExpect(status().isOk());

    }

    @Test
    public void testGetVehicle() throws Exception {

        when(vehicleServiceImpl.getVehicleByID(vehicle.getId())).thenReturn(vehicle);

        mockMvc.perform(get("/vehicle/5")).andExpect(status().isOk());
    }

    @Test
    public void testGetVehicleByVehicleID() throws Exception {

        when(vehicleServiceImpl.getVehicleByRegisterationNumber(vehicle.getRegisterationNumber())).thenReturn(vehicle);

        mockMvc.perform(get("/vehicle/search/registeration-number/123456")).andExpect(status().isOk());

    }

    @Test
    public void testDeleteVehicle() throws Exception {


        when(vehicleServiceImpl.getVehicleByID(vehicle.getId())).thenReturn(vehicle);

        mockMvc.perform(delete("/vehicle/5")).andExpect(status().isOk());
    }

    @Test
    public void testGetVehiclesByCustomerID() throws Exception {

        List<Vehicle> vehicleList = Arrays.asList(vehicle);
        when(vehicleServiceImpl.getVehicleByCustomerID(vehicle.getCustomerID())).thenReturn(vehicleList);

        mockMvc.perform(get("/vehicle/search/customerID/1")).andExpect(status().isOk());

    }

    @Test
    public void testPing() throws Exception {
        PingSignal pingSignal = new PingSignal();
        pingSignal.setRegisterationNumber("123456");

        when(vehicleServiceImpl.ping(pingSignal.getRegisterationNumber())).thenReturn(pingSignal);

        mockMvc.perform(get("/vehicle/ping/123456")).andExpect(status().isCreated());

    }

    @Test
    public void testDeleteVehiclesByCustomerID() throws Exception {
        mockMvc.perform(delete("/vehicle/search/customerID/1")).andExpect(status().isOk());
    }


}
