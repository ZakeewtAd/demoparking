package be.matthieu.demoparking.service;

import be.matthieu.demoparking.repository.dao.ParkingDao;
import be.matthieu.demoparking.repository.models.Parking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingService {
    private final ParkingDao parkingDao;

    @Autowired
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
}
