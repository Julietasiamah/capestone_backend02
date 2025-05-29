package it.epicode.capestone_backend02.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
public class AuthRunner implements ApplicationRunner {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Creazione dell'utente admin se non esiste
        Optional<AppUser> adminUser = appUserService.findByUsername("admin");
        if (adminUser.isEmpty()) {
            appUserService.registerUser("Admin", "System", "admin@example.com", "admin", "adminpwd", Set.of(Role.ROLE_ADMIN));
        }

        // Creazione dell'utente user se non esiste
        Optional<AppUser> normalUser = appUserService.findByUsername("user");
        if (normalUser.isEmpty()) {
            appUserService.registerUser("Mario", "Rossi", "user@example.com", "user", "userpwd", Set.of(Role.ROLE_USER));
        }

        // Creazione dell'utente seller se non esiste
        Optional<AppUser> normalSeller = appUserService.findByUsername("seller");
        if (normalSeller.isEmpty()) {
            appUserService.registerUser("Lucia", "Verdi", "seller@example.com", "seller", "sellerpwd",Set.of(Role.ROLE_SELLER) );
        }


    }
}
