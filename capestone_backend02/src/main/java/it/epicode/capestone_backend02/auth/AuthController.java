package it.epicode.capestone_backend02.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AppUserService appUserService;


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/current-user")
    public AppUser getCurrentUser(@AuthenticationPrincipal AppUser appUser) {
        return appUser;
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        appUserService.registerUser(
                registerRequest.getName(),
                registerRequest.getSurname(),
                registerRequest.getEmail(),
                registerRequest.getUsername(),
                registerRequest.getPassword(),


                Set.of(Role.ROLE_USER) // Assegna il ruolo di default
        );
        return ResponseEntity.ok("Registrazione avvenuta con successo");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        log.info("Login request:");
        String token = appUserService.authenticateUser(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        );
        AppUser user = appUserService.findByUsername(loginRequest.getUsername() ).orElse(null);
        return ResponseEntity.ok(new AuthResponse(token, new UserDTO(user)));
    }

//    @PutMapping ("api/user/update")
//
//    public ResponseEntity <?> updateUser (@RequestBody UpdateUserRequest request, Principal principal){
//        String username = principal.getName();
//        appUserService.updateUser(username, request);
//        return ResponseEntity.ok("User updated successfully");
//    }
//
//    @GetMapping("me")
//    @PreAuthorize("isAuthenticated()")
//    public ResponseEntity<UpdateUserRequest> getCurrentUser (Principal principal){
//        UpdateUserRequest currentUser = appUserService.getCurrentUser(principal.getName());
//        return ResponseEntity.ok(currentUser);
//    }

}
