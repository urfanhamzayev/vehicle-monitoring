package com.urfan.vehicleservice.Repository;

import com.urfan.vehicleservice.model.PingSignal;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PingSignalRepository extends PagingAndSortingRepository<PingSignal, Long> {
	
	 List<PingSignal> findByRegisterationNumber(String registerationNumber);

}
