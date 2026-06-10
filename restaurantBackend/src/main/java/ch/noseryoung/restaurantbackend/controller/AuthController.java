package ch.noseryoung.restaurantbackend.controller;

import ch.noseryoung.restaurantbackend.config.AdminSecurityProperties;
import ch.noseryoung.restaurantbackend.config.JwtService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("${app.security.admin-prefix}")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final AdminSecurityProperties adminSecurityProperties;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, AdminSecurityProperties adminSecurityProperties) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.adminSecurityProperties = adminSecurityProperties;
    }

    @PostMapping({"", "/login"})
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(resolveUsername(request.username()), request.password()));

            String token = jwtService.generateToken(authentication.getName(), authentication.getAuthorities());
            String role = extractPrimaryRole(authentication.getAuthorities());

            return ResponseEntity.ok(new LoginResponse(token, "Bearer", jwtService.getExpirationTimeMs(), role));
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    private String resolveUsername(String username) {
        if (username == null || username.isBlank()) {
            return adminSecurityProperties.getAdminUsername();
        }
        return username;
    }

    private String extractPrimaryRole(Collection<? extends GrantedAuthority> authorities) {
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority() != null) {
                return authority.getAuthority();
            }
        }
        return "ROLE_USER";
    }

    public record LoginRequest(String username, @NotBlank String password) {
    }

    public record LoginResponse(String token, String tokenType, long expiresInMs, String role) {
    }
}
