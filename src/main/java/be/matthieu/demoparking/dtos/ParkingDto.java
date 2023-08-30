package be.matthieu.demoparking.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//@Data
public class ParkingDto {
    private Long id;
    private String name;
    private Long maxCapacity;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ParkingSpotDto> spots;

    public ParkingDto() {
    }

    public ParkingDto(Long id, String name, Long maxCapacity, List<ParkingSpotDto> spots) {
        this.id = id;
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.spots = spots;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(Long maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public List<ParkingSpotDto> getSpots() {
        return spots;
    }

    public void setSpots(List<ParkingSpotDto> spots) {
        this.spots = spots;
    }
}
