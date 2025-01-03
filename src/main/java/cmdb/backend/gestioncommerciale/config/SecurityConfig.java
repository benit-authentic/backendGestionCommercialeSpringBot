package cmdb.backend.gestioncommerciale.config;

import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import cmdb.backend.gestioncommerciale.entities.Utilisateur;
import cmdb.backend.gestioncommerciale.services.UtilisateurService;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final UtilisateurService utilisateurService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService userDetailsService() {
        return username -> {
            Utilisateur utilisateur = utilisateurService.getUtilisateurParUsername(username);
            return new org.springframework.security.core.userdetails.User(
                    utilisateur.getUsername(),
                    utilisateur.getPassword(),
                    utilisateur.getRoles().stream()
                            .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getNom()))
                            .collect(Collectors.toList())
            );
        };
    }

    @Bean
     DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
     AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/admin/**").hasRole("ADMINISTRATEUR")
                .requestMatchers("/api/user/**").authenticated()
                .anyRequest().permitAll()
            )
            .formLogin(form -> form
                .loginPage("/login") // Page de connexion personnalisée si nécessaire
                .permitAll() // Permet à tous d'accéder à la page de login
            );
        return http.build();
    }
}
