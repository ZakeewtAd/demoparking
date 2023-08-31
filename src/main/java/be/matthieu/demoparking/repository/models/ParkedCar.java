package be.matthieu.demoparking.repository.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import java.time.LocalDateTime;

@Entity
@Table(name = "parked_car")
@DynamicInsert
@Getter
@Setter
public class ParkedCar {
    @Id
    String plate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "parked_since")
    LocalDateTime parkedSince;
    @ManyToOne(fetch = FetchType.LAZY)
    Parking parking;
}
