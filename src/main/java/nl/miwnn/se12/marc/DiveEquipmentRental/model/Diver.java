package nl.miwnn.se12.marc.DiveEquipmentRental.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

/**
 * @author Marc Ledermann
 * <m.ledermann@st.hanze.nl>
 * Purpose of the program:
 * Defines the Diver
 **/

@Entity
@Getter
@Setter
public class Diver implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diverId;

    @Column(nullable = false)
    private String firstName;
    private String middleName;
    @Column(nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;

    private boolean isEmployee = false;
    private boolean isAdmin = false;

    @ManyToMany
    private List<Certification> certifications = new ArrayList<>();

    public Diver(String firstName, String middleName, String lastName, Certification mpCertification,
                 String username, String password) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;

        addCertification(mpCertification);
    }

    public Diver() {
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();

        authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));

        if (isEmployee) {
            authorityList.add(new SimpleGrantedAuthority("ROLE_EMPLOYEE"));
        }

        if (isAdmin) {
            authorityList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return authorityList;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void addCertification(Certification certification) {
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
