package be.matthieu.demoparking.mappers;

import be.matthieu.demoparking.dtos.ParkedCarDto;
import be.matthieu.demoparking.repository.models.ParkedCar;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class ParkedCarMapper {
    public abstract ParkedCar parkedCarDtoToParkedCar(ParkedCarDto parkedCarDto);
//    @Mapping(
//            @Mapping(target = "parkedSince", source = "parkedSInce"),
//    )
    public abstract ParkedCarDto parkedCarToParkedCarDto(ParkedCar parkedCar);
}
