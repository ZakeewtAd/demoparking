package be.matthieu.demoparking.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

public record ParkingDto(
		Long id,
		String name,
		Long maxCapacity,
		@JsonInclude(JsonInclude.Include.NON_NULL)
		List<ParkedCarDto> parkedCars) { }
