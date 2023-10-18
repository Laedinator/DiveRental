package nl.miwnn.se12.marc.DiveEquipmentRental.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Marc Ledermann
 * <m.ledermann@st.hanze.nl>
 * Purpose of the program:
 * Defines the Certification
 **/
@Getter
@Setter
@Entity
public class Certification {

    @Id
    @GeneratedValue
    private Long certificationId;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "certification")
    private Set<Equipment> equipment;

    public Certification(String name) {
        this.name = name;
    }

    public Certification() {
        this("None");
    }

}
