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
 * Defines the Diver
 **/

@Entity
@Getter
@Setter
public class Diver {
    @Id
    @GeneratedValue
    private Long diverId;

    @Column(nullable = false)
    private String firstName;
    private String middleName;
    @Column(nullable = false)
    private String lastName;

    @ManyToMany
    private Set<Certification> certifications = new HashSet<>();


    public Diver(String firstName, String middleName, String lastName, Certification mpCertification) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        setCertifications(mpCertification);
    }

    public Diver() {
    }

    public void setCertifications(Certification certification) {
        certifications.add(certification);
    }

    public String getDisplayName() {
        String displayName = firstName;

        if (!middleName.isEmpty()) {
            displayName += " " + middleName;
        }

        displayName += " " + lastName;
        return displayName;
    }

    public String getDiverCertificationString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Certification certification : certifications) {
            stringBuilder.append(certification.getName());
            if (certifications.size() > 1) {
                stringBuilder.append("\n");
            }
        }

        return stringBuilder.toString();
    }

}
