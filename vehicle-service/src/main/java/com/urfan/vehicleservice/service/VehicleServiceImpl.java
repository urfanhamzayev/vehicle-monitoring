package com.urfan.vehicleservice.service;

import com.urfan.vehicleservice.Repository.PingSignalRepository;
import com.urfan.vehicleservice.Repository.VehicleRepository;
import com.urfan.vehicleservice.exception.VehicleNotFoundException;
import com.urfan.vehicleservice.model.OnlineStatus;
import com.urfan.vehicleservice.model.PingSignal;
import com.urfan.vehicleservice.model.Vehicle;
import com.urfan.vehicleservice.model.VehicleStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;


    @Autowired
    PingSignalRepository pingSignalRepository;

    /**
     * Get all the stored vehicles.
     */
    @Override
    public List<Vehicle> getVehicles() {
        return (List<Vehicle>) vehicleRepository.findAll();
    }

    /**
     * Get all the vehicles by Status.
     */
    @Override
    public List<Vehicle> getVhiclesByStatus(String status) {
        System.out.println(status);
        return vehicleRepository.findVehicleByStatus(status);
    }

    /**
     * The Vehicle owned by a specific customer.
     */
    @Override
    public List<Vehicle> getVehicleByCustomerID(Long customerID) {
        return vehicleRepository.findByCustomerID(customerID);
    }
    /**
     * Get the vehicle using the database identifier.
     */
    @Override
    public Vehicle getVehicleByID(Long id) {
        return vehicleRepository.findById(id).orElseThrow(VehicleNotFoundException::new);
    }

    /**
     * Get the vehicle using the registration number.
     */
    @Override
    public Vehicle getVehicleByRegisterationNumber(String registerationNumber) {
        return vehicleRepository.findByRegisterationNumber(registerationNumber).orElseThrow(VehicleNotFoundException::new);

    }

    /**
     * Handle the vehicle signal.
     */
    @Transactional
    @Override
    public PingSignal ping(String registerationNumber) {
        PingSignal pingSignal = new PingSignal();

        // update the Vehicle status
        Vehicle vehicle = vehicleRepository.findByRegisterationNumber(registerationNumber).orElseThrow(VehicleNotFoundException::new);

        pingSignal.setRegisterationNumber(registerationNumber);

        if (vehicle != null) {
            vehicle.setStatus(OnlineStatus.ONLINE);
            vehicle.setLastPingDate(ZonedDateTime.now());
            vehicleRepository.save(vehicle);
        }
        return pingSignalRepository.save(pingSignal);
    }

    /**
     * Store the vehicle into the database.
     */

    @Transactional
    @Override
    public Vehicle save(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    /**
     * Overview about the percentage of the Online/Offline vehicle.
     */

    @Override
    public VehicleStatus getVehicleStats() {

        Long totalVehiles = vehicleRepository.count();
        Long onlineVhicleCount = vehicleRepository.countByStatus(OnlineStatus.ONLINE);

        VehicleStatus vehicleStats = new VehicleStatus();
        vehicleStats.setTotal(totalVehiles);
        vehicleStats.setOnlineCount(onlineVhicleCount);
        vehicleStats.setOfflineCount(totalVehiles - onlineVhicleCount);
        return vehicleStats;
    }

    /**
     * Delete vehicle owned by a specific customer.
     */
    @Transactional
    @Override
    public void deleteVehicleByCustomerID(Long customerID) {
        vehicleRepository.deleteByCustomerID(customerID);
    }

    /**
     * Delete a Vehicle by the Database generated identifier.
     */
    @Transactional
    @Override
    public void delete(Long id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        vehicleRepository
                .delete(vehicle.orElseThrow(() -> new RuntimeException("Vehicle with " + id + " does not exist.")));

    }

    /**
     * Job That invalidates the vehicle status which is not had any update signal
     * from a minute of more.
     */
    @Scheduled(fixedDelay = 60000)
    @Transactional
    public void vehicleStatusInvalidatorJob() {
        ZonedDateTime invalidationTime = ZonedDateTime.now().minusSeconds(60);
        vehicleRepository.invalidateOnlineVehicleStatus(invalidationTime);
    }
}
