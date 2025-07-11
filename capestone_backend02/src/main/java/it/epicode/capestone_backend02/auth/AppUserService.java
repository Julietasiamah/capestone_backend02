package it.epicode.capestone_backend02.auth;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public AppUser registerUser(String name, String surname, String email,String username, String password, Set<Role> roles) {
        if (appUserRepository.existsByUsername(username)) {
            throw new EntityExistsException("Username già in uso");
        }
        if (appUserRepository.existsByEmail(email)) {
            throw new EntityExistsException("Email già in uso");
        }

        AppUser appUser = new AppUser();
        appUser.setName(name);
        appUser.setSurname(surname);
        appUser.setEmail(email);
        appUser.setUsername(username);
        appUser.setPassword(passwordEncoder.encode(password));
        appUser.setRoles(roles);
        return appUserRepository.save(appUser);
    }

    public Optional<AppUser> findByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    public String authenticateUser(String username, String password)  {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            throw new SecurityException("Credenziali non valide", e);
        }
    }



//
//    public AppUser getUserByUsername(String username)  {
//        AppUser appUser = appUserRepository.findByUsername(username)
//            .orElseThrow(() -> new EntityNotFoundException("Utente non trovato con username: " + username));
//
//
//        return appUser;
//    }

//    public void updateUser (String username, UpdateUserRequest request){
//        AppUser appUser = appUserRepository.findByUsername(username)
//                .orElseThrow(() -> new EntityNotFoundException("Utente non trovato con username: " + username));
//
//        appUser.setEmail(request.getEmail());
//        appUser.setUsername(request.getUsername());
//        appUser.setPassword(passwordEncoder.encode(request.getPassword()));
//
//        appUserRepository.save(appUser);
//    }
//
//    public UpdateUserRequest getCurrentUser(String username) {
//        AppUser appUser = appUserRepository.findByUsername(username)
//                .orElseThrow(() -> new EntityNotFoundException("Utente non trovato con username: " + username));
//
//        return new UpdateUserRequest(appUser.getEmail(), appUser.getUsername(), appUser.getPassword());
//    }


}
