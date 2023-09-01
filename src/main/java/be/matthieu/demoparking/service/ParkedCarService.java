package be.matthieu.demoparking.service;

import be.matthieu.demoparking.dtos.ParkedCarDto;
import be.matthieu.demoparking.exceptions.NotExistingCarException;
import be.matthieu.demoparking.exceptions.NotExistingParkingException;
import be.matthieu.demoparking.mappers.ParkedCarMapper;
import be.matthieu.demoparking.repository.dao.ParkedCarDao;
import be.matthieu.demoparking.repository.models.ParkedCar;
import be.matthieu.demoparking.repository.models.Parking;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ParkedCarService {

    private final ParkedCarDao parkedCarDao;
    private final ParkingService parkingService;

    public ParkedCarService(ParkedCarDao parkedCarDao, ParkingService parkingService) {
        this.parkedCarDao = parkedCarDao;
        this.parkingService = parkingService;
    }

    public ParkedCar parkCarIn(Parking parking, String parkedCarPlate) {
        ParkedCar parkedCar = new ParkedCar();
        parkedCar.setPlate(parkedCarPlate);
        parkedCar.setParking(parking);
        parkedCar.setParkedSince(LocalDateTime.now());

        return parkedCarDao.saveAndFlush(parkedCar);
    }

    public void removeCar(ParkedCar parkedCar) {
       if(!parkedCarDao.exists(Example.of(parkedCar))) {
           throw new NotExistingCarException();
       }
        parkedCarDao.delete(parkedCar);
    }

    public List<ParkedCar> getCarsByParkingId(Long parkingId) {
        Parking parking = parkingService.findById(parkingId).orElseThrow(NotExistingParkingException::new);
        return parkedCarDao.findParkedCarsByParking(parking);
    }
}
