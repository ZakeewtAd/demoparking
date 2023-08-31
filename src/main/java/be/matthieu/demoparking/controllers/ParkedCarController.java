package be.matthieu.demoparking.controllers;

import be.matthieu.demoparking.business.ParkedCarBusinessManager;
import be.matthieu.demoparking.dtos.ParkedCarDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("car")
public class ParkedCarController {


    private ParkedCarBusinessManager parkedCarBusinessManager;

    public ParkedCarController(ParkedCarBusinessManager parkedCarBusinessManager) {
        this.parkedCarBusinessManager = parkedCarBusinessManager;
    }

    @GetMapping("/{parkingId}")
    public List<ParkedCarDto> getCarsByParkingId(@PathVariable("parkingId") Long parkingId) {
        return parkedCarBusinessManager.getCarsByParkingId(parkingId);
    }

    @DeleteMapping
    public void removeCar(@RequestBody ParkedCarDto parkedCarDto) {
        parkedCarBusinessManager.removeCar(parkedCarDto);
    }
}
