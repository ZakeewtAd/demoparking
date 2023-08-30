package be.matthieu.demoparking.controllers;

import be.matthieu.demoparking.business.ParkingBusinessManager;
import be.matthieu.demoparking.dtos.ParkingDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("parking")
public class ParkingController {

    private final ParkingBusinessManager parkingBusinessManager;

    public ParkingController(ParkingBusinessManager parkingBusinessManager) {
        this.parkingBusinessManager = parkingBusinessManager;
    }

    @GetMapping
    public List<ParkingDto> listParking() {
        return parkingBusinessManager.getAllParking();
    }

    @ResponseBody
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ParkingDto createParking(@RequestBody ParkingDto parkingDto) {
        return parkingBusinessManager.saveOrUpdate(parkingDto);
    }

    @DeleteMapping
    public void removeParking() {
        // does nothing yet
    }
}
