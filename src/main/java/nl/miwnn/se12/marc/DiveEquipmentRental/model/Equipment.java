package nl.miwnn.se12.marc.DiveEquipmentRental.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Marc Ledermann
 * <m.ledermann@st.hanze.nl>
 * Defines the attributes of my equipment.
 **/

@Entity
public class Equipment {
    @Id @GeneratedValue
    private Long equipmentId;

    private String type;
    private String name;
    private String certificationNeeded;

}
