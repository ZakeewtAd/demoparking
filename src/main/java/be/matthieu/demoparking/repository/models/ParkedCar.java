package be.matthieu.demoparking.repository.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import java.time.LocalDateTime;

@Entity
@Table(name = "parked_car")
@DynamicInsert
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParkedCar {
    @Id
    String plate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "parked_since")
    LocalDateTime parkedSince;
    @ManyToOne(fetch = FetchType.LAZY)
    Parking parking;
}
