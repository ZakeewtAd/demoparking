package be.matthieu.demoparking.business;

import be.matthieu.demoparking.dtos.ParkedCarDto;
import be.matthieu.demoparking.dtos.ParkingDto;
import be.matthieu.demoparking.exceptions.MaxCapacityReachException;
import be.matthieu.demoparking.exceptions.NotExistingParkingException;
import be.matthieu.demoparking.mappers.ParkedCarMapper;
import be.matthieu.demoparking.mappers.ParkingMapper;
import be.matthieu.demoparking.repository.models.ParkedCar;
import be.matthieu.demoparking.repository.models.Parking;
import be.matthieu.demoparking.service.ParkedCarService;
import be.matthieu.demoparking.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ParkingBusinessManager {

    private final ParkingService parkingService;
    private final ParkedCarService parkedCarService;
    private final ParkingMapper parkingMapper;
    private final ParkedCarMapper parkedCarMapper;

    public ParkingBusinessManager(ParkingService parkingService, ParkedCarService parkedCarService, ParkingMapper parkingMapper, ParkedCarMapper parkedCarMapper) {
        this.parkingService = parkingService;
        this.parkedCarService = parkedCarService;
        this.parkingMapper = parkingMapper;
        this.parkedCarMapper = parkedCarMapper;
    }

    public List<ParkingDto> getAllParking() {
        return parkingService.findAllParking().stream().map(parkingMapper::parkingToParkingDto).collect(Collectors.toList());
    }

    public ParkingDto saveOrUpdate(ParkingDto parkingDto) {
        Parking parking = parkingService.saveOrUpdate(parkingMapper.parkingDtoToParking(parkingDto));
        return parkingMapper.parkingToParkingDto(parking);
    }

    public ParkedCarDto parkCar(Long parkingId, String parkedCarPlate) {
        Parking parking = parkingService.findById(parkingId).orElseThrow(NotExistingParkingException::new);
        if(parking.getMaxCapacity() <= parking.getParkedCars().size()) {
            throw new MaxCapacityReachException(parking.getName());
        }
        ParkedCar parkedCar = parkedCarService.parkCarIn(parking, parkedCarPlate);

        return parkedCarMapper.parkedCarToParkedCarDto(parkedCar);
    }

    public ParkingDto findParkingById(Long parkingId) {
        Parking parking = parkingService.findById(parkingId).orElseThrow(NotExistingParkingException::new);
        return parkingMapper.parkingToParkingDto(parking);
    }
}
