package be.matthieu.demoparking.business;

import be.matthieu.demoparking.dtos.ParkedCarDto;
import be.matthieu.demoparking.dtos.ParkingDto;
import be.matthieu.demoparking.exceptions.MaxCapacityReachException;
import be.matthieu.demoparking.mappers.ParkedCarMapper;
import be.matthieu.demoparking.mappers.ParkedCarMapperImpl;
import be.matthieu.demoparking.mappers.ParkingMapper;
import be.matthieu.demoparking.mappers.ParkingMapperImpl;
import be.matthieu.demoparking.repository.models.ParkedCar;
import be.matthieu.demoparking.repository.models.Parking;
import be.matthieu.demoparking.service.ParkedCarService;
import be.matthieu.demoparking.service.ParkingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {ParkedCarMapperImpl.class, ParkingMapperImpl.class})
class ParkingBusinessManagerTest {

    private ParkingBusinessManager parkingBusinessManager;
    @Mock
    private ParkingService parkingService;
    @Mock
    private ParkedCarService parkedCarService;
    @Autowired
    private ParkingMapper parkingMapper;
    @Autowired
    private ParkedCarMapper parkedCarMapper;

    @BeforeEach
    void setUp() {
        parkingBusinessManager = new ParkingBusinessManager(parkingService, parkedCarService, parkingMapper, parkedCarMapper);
    }

    @Test
    void getAllParking() {
        when(parkingService.findAllParking()).thenReturn(List.of(
                createParking(1L, "Parking #1", 50L,
                        List.of(createParkedCar("1-AAA-333"), createParkedCar("1-AAA-444"))
                ),
                createParking(2L, "Parking #2", 100L, Collections.singletonList(createParkedCar("3-DDD-666"))),
                createParking(3L, "Parking #3", 150L, null)
        ));
        List<ParkingDto> parkingDtos = parkingBusinessManager.getAllParking();

        assertEquals(3, parkingDtos.size());
        assertEquals("Parking #2", parkingDtos.get(1).name());
        assertEquals(2, parkingDtos.get(0).parkedCars().size());
    }

    @Test
    void parkCar() {
        when(parkingService.findById(1L))
                .thenReturn(Optional.of(createParking(1L, "name", 2L, Collections.emptyList())));
        when(parkedCarService.parkCarIn(any(Parking.class), anyString())).thenReturn(createParkedCar("1-DSU-321"));
        ParkedCarDto parkedCarDto = parkingBusinessManager.parkCar(1L, "1-DSU-321");

        assertNotNull(parkedCarDto);
        assertEquals("1-DSU-321", parkedCarDto.plate());
        assertNotNull(parkedCarDto.parkedSince());
    }

    @Test()
    void parkCarThrowMaxCapacityReachException() {
        when(parkingService.findById(1L))
                .thenReturn(Optional.of(createParking(1L, "name", 1L,
                        Collections.singletonList(createParkedCar("1-AAA-111")))));
        when(parkedCarService.parkCarIn(any(Parking.class), anyString())).thenReturn(createParkedCar("1-DSU-321"));
        assertThrows(MaxCapacityReachException.class, () -> parkingBusinessManager.parkCar(1L, "1-DSU-321"));
    }


    private static Parking createParking(Long id, String name, Long maxCapacity, List<ParkedCar> parkedCars) {
        return new Parking(id, name, maxCapacity, parkedCars);
    }

    private static ParkedCar createParkedCar(String plate) {
        return new ParkedCar(plate, LocalDateTime.now(), null);
    }
}