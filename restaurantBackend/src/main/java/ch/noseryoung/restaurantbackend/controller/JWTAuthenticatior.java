package ch.noseryoung.restaurantbackend.controller;
import ch.noseryoung.restaurantbackend.config.AdminSecurityProperties;
import ch.noseryoung.restaurantbackend.config.JwtService;
import ch.noseryoung.restaurantbackend.model.Users;
import ch.noseryoung.restaurantbackend.service.LoginService;
import ch.noseryoung.restaurantbackend.service.MenuService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequiredArgsConstructor
public class JWTAuthenticatior {
    private final LoginService loginService;
    private final JwtService jwtService;
    @GetMapping("/JWTGen")
    public ResponseEntity<String> GetJWT(@Valid @RequestBody Users data){
        System.out.println("User");
        String usern = data.getUsername();
        String passw = data.getPassword();
        if (loginService.authenticate(passw,usern)){
            System.out.println("user Found");
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
            return ResponseEntity.ok().body(jwtService.generateToken(usern, Collections.emptyList()));
        }else{
            System.out.println("user Not Found");
            return ResponseEntity.badRequest().body("Invalid Username or Password");
        }
    }
}
