package dat3.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Driver {
    public Driver(String name, String surname, BigDecimal salary) {
        this.name = name;
        this.surname = surname;
        this.salary = salary;
    }
    @Id
    private String id;

    @Column(name = "employement_date")
    @Temporal(TemporalType.DATE)
    private Date employementDate;

    @Column(name = "name")
    private String name;

    @Column(name = "salary")
    private BigDecimal salary;

    @Column(name = "surname")
    private String surname;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private WasteTruck wasteTruckId;

    @PrePersist
    private void prePersist() {
        this.employementDate = new Date();
        this.id = generateID();
    }

    /*
    * The id for the driver should be a string with the format ddMMyy-XX-XXXL.

    ddMMyy is the date of employment, (Date: 2023-08-26 -> String: 230826)
    XX is the first letters of the name and of the surname (Name: John Doe -> String: JD)
    XXX is a random number between 100 and 999
    L is the last letter of the surname (Surname: Doe -> String: E)
    */
    private String generateID() {
        String date = employementDate.toString().substring(2, 4) + employementDate.toString().substring(5, 7) + employementDate.toString().substring(8, 10);
        String name = String.valueOf(this.name.charAt(0) + this.surname.charAt(0));
        String random = String.valueOf((int) (Math.random() * (999 - 100 + 1) + 100));
        String lastLetter = String.valueOf(this.surname.charAt(this.surname.length() - 1));
        return date + "-" + name + "-" + random + lastLetter;
    }

    public Boolean validateDriverId(String driverId) {
        return driverId.matches("[0-9][0-9][0-9][0-9][0-9][0-9]-[A-Z][A-Z]-[0-9][0-9][0-9][A-Z]");
    }
}
