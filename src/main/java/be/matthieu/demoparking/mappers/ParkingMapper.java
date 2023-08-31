package be.matthieu.demoparking.mappers;

import be.matthieu.demoparking.dtos.ParkingDto;
import be.matthieu.demoparking.repository.models.Parking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = ParkedCarMapper.class)
public abstract class ParkingMapper {
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "maxCapacity", source = "maxCapacity"),
            @Mapping(target = "parkedCars", source = "parkedCars")
    })
    public abstract ParkingDto parkingToParkingDto(Parking parking);
    public abstract Parking parkingDtoToParking(ParkingDto parkingDto);
}
