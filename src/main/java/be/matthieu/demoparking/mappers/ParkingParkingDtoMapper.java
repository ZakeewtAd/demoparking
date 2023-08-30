package be.matthieu.demoparking.mappers;

import be.matthieu.demoparking.dtos.ParkingDto;
import be.matthieu.demoparking.repository.models.Parking;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ParkingParkingDtoMapper {
    public abstract ParkingDto parkingToParkingDto(Parking parking);
    public abstract Parking parkingDtoToParking(ParkingDto parkingDto);
}
