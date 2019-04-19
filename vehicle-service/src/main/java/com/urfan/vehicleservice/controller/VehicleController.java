package com.urfan.vehicleservice.controller;

import com.urfan.vehicleservice.model.PingSignal;
import com.urfan.vehicleservice.model.Vehicle;
import com.urfan.vehicleservice.model.VehicleStatus;
import com.urfan.vehicleservice.service.VehicleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/vehicle")
@Api(value = "Vehicle API")
@CrossOrigin("*")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @ApiOperation(value = "List All Vehicles.")
    @GetMapping
    public List<Vehicle> getVehicles() {
        return vehicleService.getVehicles();
    }

    @ApiOperation(value = "Get a vehicle by the id.")
    @GetMapping(path = "{id}")
    public Vehicle getVehicleByID(@PathVariable Long id) {
        return vehicleService.getVehicleByID(id);
    }

    @ApiOperation(value = "Delete a vehicle by the id.")
    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteVehicleByID(@PathVariable Long id) {
        vehicleService.delete(id);
    }

    @ApiOperation(value = "Get a vehicle by the vehicle Status.")
    @GetMapping(path = "search/status/{status}")
    public List<Vehicle> getVehicleByStatusVehicleID(@PathVariable String status ) {
        return vehicleService.getVhiclesByStatus(status);
    }

    @ApiOperation(value = "Get a vehicle by the registerationNumber.")
    @GetMapping(path = "search/registeration-number/{registerationNumber}")
    public Vehicle getVehicleByVehicleID(@PathVariable String registerationNumber) {
        return vehicleService.getVehicleByRegisterationNumber(registerationNumber);
    }

    @ApiOperation(value = "Get a vehicle by the customerID.")
    @GetMapping(path = "search/customerID/{customerID}")
    public List<Vehicle> getVehiclesByCustomerID(@PathVariable Long customerID) {
        return vehicleService.getVehicleByCustomerID(customerID);
    }

    @ApiOperation(value = "Delete all vehicles related to the customerID.")
    @DeleteMapping(path = "search/customerID/{customerID}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteVehicleByVehicleID(@PathVariable Long customerID) {
        vehicleService.deleteVehicleByCustomerID(customerID);
    }

    @ApiOperation(value = "Add a new Vehicle.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestBody Vehicle vehicle) {
        vehicleService.save(vehicle);
    }

    @ApiOperation(value = "Update a Vehicle.")
    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void update(@Valid @RequestBody Vehicle vehicle) {
        this.save(vehicle);
    }

    @ApiOperation(value = "Ping Signal for a registerationNumber.")
    @GetMapping(path = "ping/{registerationNumber}")
    @ResponseStatus(HttpStatus.CREATED)
    public PingSignal ping(@PathVariable String registerationNumber) {
        return vehicleService.ping(registerationNumber);
    }

    @ApiOperation(value = "Get the Vehicle Statistics.")
    @GetMapping(path = "stats")
    @ResponseStatus(HttpStatus.CREATED)
    public VehicleStatus getVehicleStats() {
        return vehicleService.getVehicleStats();
    }

}
