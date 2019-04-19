package com.urfan.vehicleservice.service;

import com.urfan.vehicleservice.Repository.VehicleRepository;
import com.urfan.vehicleservice.model.OnlineStatus;
import com.urfan.vehicleservice.model.Vehicle;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VehicleServiceImplTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private VehicleServiceImpl vehicleService;

    Vehicle vehicle =null;

    @Before
    public void setData(){
        vehicle = new Vehicle();
        vehicle.setId(1l);
        vehicle.setVehicleID("12345678901234567");
        vehicle.setRegisterationNumber("124356");
        vehicle.setCustomerID(Long.parseLong("1221"));
        vehicle.setStatus(OnlineStatus.OFFLNE);
    }


    @Test
    public void testGetVehicleByID() {
        when(vehicleRepository.findById(1l)).thenReturn(Optional.of(vehicle));

        Vehicle retrunedVehicle = vehicleService.getVehicleByID(1l);
        assertEquals(vehicle, retrunedVehicle);
    }

    @Test
    public void testSave() {
        when(vehicleRepository.save(vehicle)).thenReturn(vehicle);

        Vehicle savedVehicle = vehicleService.save(vehicle);
        assertEquals(vehicle, savedVehicle);
    }

    @Test
    public void testGetVehicles() {
        when(vehicleRepository.findAll()).thenReturn(Arrays.asList(vehicle, vehicle));

        Collection<Vehicle> retrunedVehicles = vehicleService.getVehicles();
        assertEquals(2, retrunedVehicles.size());
    }


    @Test
    public void testGetVehicleByCustomerID () {

        when(vehicleRepository.findByCustomerID(vehicle.getCustomerID())).thenReturn(Arrays.asList(vehicle));

        Collection<Vehicle> retrunedVehicles = vehicleService.getVehicleByCustomerID(vehicle.getCustomerID());
        assertEquals(1, retrunedVehicles.size());
    }


}


