package be.matthieu.demoparking.business;

import be.matthieu.demoparking.dtos.ParkedCarDto;
import be.matthieu.demoparking.mappers.ParkedCarMapper;
import be.matthieu.demoparking.repository.models.ParkedCar;
import be.matthieu.demoparking.service.ParkedCarService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ParkedCarBusinessManager {
    private final ParkedCarService parkedCarService;
    private final ParkedCarMapper parkedCarMapper;

    public ParkedCarBusinessManager(ParkedCarService parkedCarService, ParkedCarMapper parkedCarMapper) {
        this.parkedCarService = parkedCarService;
        this.parkedCarMapper = parkedCarMapper;
    }

    public void removeCar(ParkedCarDto parkedCarDto) {
        parkedCarService.removeCar(parkedCarMapper.parkedCarDtoToParkedCar(parkedCarDto));
    }

    public List<ParkedCarDto> getCarsByParkingId(Long parkingId) {
        return parkedCarService.getCarsByParkingId(parkingId).stream().map(parkedCarMapper::parkedCarToParkedCarDto).collect(Collectors.toList());
    }
}
