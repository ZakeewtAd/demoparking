package be.matthieu.demoparking.repository.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

//@Data
@Entity
public class Parking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long maxCapacity;
    private String name;

    public Parking() {
    }

    public Parking(Long maxCapacity, String name) {
        this.maxCapacity = maxCapacity;
        this.name = name;
    }

    public Parking(Long id, Long maxCapacity, String name) {
        this.id = id;
        this.maxCapacity = maxCapacity;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(Long maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
