package be.matthieu.demoparking.repository.dao;

import be.matthieu.demoparking.repository.models.ParkedCar;
import be.matthieu.demoparking.repository.models.Parking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkedCarDao extends JpaRepository<ParkedCar, String> {
    List<ParkedCar> findParkedCarsByParking(Parking parking);
}
