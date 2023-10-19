package nl.miwnn.se12.marc.DiveEquipmentRental.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Marc Ledermann
 * <m.ledermann@st.hanze.nl>
 * Defines the attributes of the equipment in the shop.
 **/

@Setter
@Getter
@Entity
public class Equipment {
    @Id
    @GeneratedValue
    private Long equipmentId;

    @Column(nullable = false)
    private String name;
    private String type;
    private String size;
    private String brand;

    @ManyToOne
    private Certification certification;

    @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL)
    private Set<Rental> rentals;


    public Equipment(String name, String type, String size, String brand, Certification mpCertification) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.brand = brand;
        this.certification = mpCertification;
    }

    public Equipment() {
    }

    public int getNumberOfAvailableRentals() {
        int count = 0;
        for (Rental rental : rentals) {
            if (rental.getAvailable()) {
                count++;
            }
        }
        return count;
    }


}
