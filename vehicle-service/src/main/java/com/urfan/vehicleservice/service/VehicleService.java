package com.urfan.vehicleservice.service;

import com.urfan.vehicleservice.model.PingSignal;
import com.urfan.vehicleservice.model.Vehicle;
import com.urfan.vehicleservice.model.VehicleStatus;

import java.util.List;

public interface VehicleService {


    List<Vehicle> getVehicles();


    List<Vehicle> getVhiclesByStatus(String status);


    List<Vehicle> getVehicleByCustomerID(Long customerID);


    Vehicle getVehicleByID(Long id);


    Vehicle getVehicleByRegisterationNumber(String registerationNumber);


    PingSignal ping(String registerationNumber);


    Vehicle save(Vehicle vehicle);


    VehicleStatus getVehicleStats();

    void deleteVehicleByCustomerID(Long customerID);


    void delete(Long id);

}
