package be.matthieu.demoparking.dtos;

import lombok.Data;

@Data
public class ParkingSpotDto {
    private Long id;
    private ParkedCarDto car;
}
