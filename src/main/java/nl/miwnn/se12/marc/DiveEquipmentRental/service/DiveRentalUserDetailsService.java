package nl.miwnn.se12.marc.DiveEquipmentRental.service;

import lombok.RequiredArgsConstructor;
import nl.miwnn.se12.marc.DiveEquipmentRental.repository.RentalUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Marc Ledermann
 * <m.ledermann@st.hanze.nl>
 * Purpose of the program:
 * Provide the details about a user
 **/
@Service
@RequiredArgsConstructor
public class DiveRentalUserDetailsService implements UserDetailsService {

    private final RentalUserRepository rentalUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return rentalUserRepository.findRentalUserByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User: %s does not exist", username))
        );
    }
}
