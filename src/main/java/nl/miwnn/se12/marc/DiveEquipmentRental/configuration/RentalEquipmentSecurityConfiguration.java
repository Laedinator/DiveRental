package nl.miwnn.se12.marc.DiveEquipmentRental.configuration;

import lombok.RequiredArgsConstructor;
import nl.miwnn.se12.marc.DiveEquipmentRental.service.DiveRentalUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Marc Ledermann
 * <m.ledermann@st.hanze.nl>
 * Purpose of the program:
 * Configure the Security for the rental Equipment app.
 **/

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class RentalEquipmentSecurityConfiguration {
    private final DiveRentalUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests((authorize) -> authorize
                        .antMatchers("/css/**", "/webjars/**").permitAll()
                        .antMatchers("/initialize").permitAll()
                        .antMatchers("/", "/equipment/overview").permitAll()
                        .antMatchers("/rental/new", "/diver", "equipment/new").hasAnyRole("ADMIN", "EMPLOYEE")
                        .anyRequest().authenticated())
                .formLogin().and()
                .logout().logoutSuccessUrl("/equipment/overview");

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

}
