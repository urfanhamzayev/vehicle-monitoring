package com.alten.vehiclesignalgenerator.job;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Predicate;

import com.alten.vehiclesignalgenerator.dto.Vehicle;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class StatusGenerationJob {

	@Value("${vehicle.service.url}")
	private String vehicleServiceURL;

	@Value("${vehicle.ping.url}")
	private String vehiclePingURL;

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Vehicle send Status per minute
	 */
	@Scheduled(fixedDelay=60000)
	private void generateSignal() throws IOException {
		System.out.println("The Job started");

		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");

		Map<String, String> params = new HashMap<String, String>();


		HttpEntity entity = new HttpEntity(headers);

		HttpEntity<String> response = restTemplate.exchange(vehicleServiceURL, HttpMethod.GET, entity, String.class, params);

		System.out.println(response);
		ObjectMapper mapper = new ObjectMapper();

		List<Vehicle> vehicleJsonList = mapper.readValue(response.getBody(), new TypeReference<List<Vehicle>>(){});


		System.out.println(vehicleJsonList);

		
		Random random = new Random();
 		Predicate<Vehicle> toBeOnline = v-> random.nextBoolean();
		
		vehicleJsonList.stream().filter(toBeOnline).forEach(v->sendPingSignal(v.getRegisterationNumber()));;
		
	}
	
	
	private void sendPingSignal(String vehicleRegistrationNumber) {

		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");

		Map<String, String> params = new HashMap<String, String>();


		HttpEntity entity = new HttpEntity(headers);

		HttpEntity<String> response = restTemplate.exchange(vehiclePingURL + vehicleRegistrationNumber, HttpMethod.GET, entity, String.class, params);

		System.out.println(response);
		System.out.println("The vehicle " + vehicleRegistrationNumber + " should be online");
	}

}
