package nl.miwnn.se12.marc.DiveEquipmentRental.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Marc Ledermann
 * <m.ledermann@st.hanze.nl>
 * Purpose of the program:
 * Defines the rental equipment.
 **/
@Entity
@Getter
@Setter
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rentalId;

    private Boolean available = true;

    @ManyToOne
    private Equipment equipment;

    public Rental(Equipment equipment) {
        this.equipment = equipment;
    }

    public Rental() {
    }
}
