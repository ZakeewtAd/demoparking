package be.matthieu.demoparking.service;

import be.matthieu.demoparking.repository.dao.ParkingDao;
import be.matthieu.demoparking.repository.models.Parking;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingService {
    private final ParkingDao parkingDao;

    public ParkingService(ParkingDao parkingDao){
        this.parkingDao = parkingDao;
    }

    public Parking saveOrUpdate(Parking parking) {
        return parkingDao.save(parking);
    }

    public List<Parking> findAllParking() {
        return parkingDao.findAll();
    }

    public void deleteAll() {
        parkingDao.deleteAll();
    }

    public Optional<Parking> findById(Long parkingId) {
        return parkingDao.findById(parkingId);
    }
}
