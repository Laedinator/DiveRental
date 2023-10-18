package nl.miwnn.se12.marc.DiveEquipmentRental.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
    @GeneratedValue
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
