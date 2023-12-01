package nl.miwnn.se12.marc.DiveEquipmentRental.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Marc Ledermann
 * <m.ledermann@st.hanze.nl>
 * Purpose of the program:
 * defines the DTO of a certification.
 **/

@Getter
@Setter
@Builder
public class CertificationDto {
    private Long certificationId;
    private String certificationName;
}
