package be.matthieu.demoparking.business;

import be.matthieu.demoparking.dtos.ParkingDto;
import be.matthieu.demoparking.mappers.ParkingParkingDtoMapper;
import be.matthieu.demoparking.repository.models.Parking;
import be.matthieu.demoparking.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ParkingBusinessManager {

    private final ParkingService parkingService;
    private final ParkingParkingDtoMapper parkingParkingDtoMapper;

    @Autowired
    public ParkingBusinessManager(ParkingService parkingService, ParkingParkingDtoMapper parkingParkingDtoMapper) {
        this.parkingService = parkingService;
        this.parkingParkingDtoMapper = parkingParkingDtoMapper;
    }

    public List<ParkingDto> getAllParking() {
        return parkingService.findAllParking().stream().map(parkingParkingDtoMapper::parkingToParkingDto).collect(Collectors.toList());
    }

    public ParkingDto saveOrUpdate(ParkingDto parkingDto) {
        Parking parking = parkingService.saveOrUpdate(parkingParkingDtoMapper.parkingDtoToParking(parkingDto));
        return parkingParkingDtoMapper.parkingToParkingDto(parking);
    }
}
