package dat3.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class WasteTruck {
    public WasteTruck(String brand, int capacity, String registrationNumber) {
        this.brand = brand;
        this.capacity = capacity;
        this.registrationNumber = registrationNumber;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Access(AccessType.PROPERTY)
    private int id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "is_available")
    private boolean isAvailable;

    @Column(name = "registration_number")
    private String registrationNumber;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "wasteTruckId", cascade = CascadeType.PERSIST)
    private Set<Driver> drivers;
}
