package be.matthieu.demoparking.repository.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "parking")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Parking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long maxCapacity;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parking", cascade = CascadeType.ALL)
    private List<ParkedCar> parkedCars;
}
