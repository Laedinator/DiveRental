package nl.miwnn.se12.marc.DiveEquipmentRental.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

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

    private String name;
    private String type;
    private String size;
    private String brand;
    private String certification;

    @OneToMany(mappedBy = "equipment")
    private List<Rental> rentals;

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
