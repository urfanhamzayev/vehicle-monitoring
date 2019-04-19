
package com.urfan.vehicleservice.Repository;


import com.urfan.vehicleservice.model.Vehicle;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends PagingAndSortingRepository<Vehicle, Long> {
	
	 Optional<Vehicle> findByRegisterationNumber(String registerationNumber);

	 List<Vehicle> findByCustomerID(Long customerID);

	 List<Vehicle> deleteByCustomerID(Long customerID);
	
	 List<Vehicle> findVehicleByStatus(String status);

	 long countByStatus(String status);

	@Modifying
	@Query("UPDATE Vehicle d SET d.status = 'OFFLINE' WHERE d.status = 'ONLINE' and d.lastPingDate < ?1")
	public int invalidateOnlineVehicleStatus(ZonedDateTime invalidationTime);
}