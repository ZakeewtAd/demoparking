package be.matthieu.demoparking.repository.dao;

import be.matthieu.demoparking.repository.models.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingDao extends JpaRepository<Parking, Long> {
}
