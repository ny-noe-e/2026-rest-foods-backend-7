package ch.noseryoung.restaurantbackend.config;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final String adminPrefix;
    private final String adminUsername;
    private final String adminPassword;
    private final String adminRole;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter, @Value("${app.security.admin-prefix}") String adminPrefix, @Value("${app.security.admin-username}") String adminUsername, @Value("${app.security.admin-password}") String adminPassword, @Value("${app.security.admin-role:ADMIN}") String adminRole) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.adminPrefix = adminPrefix;
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
        this.adminRole = adminRole;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationEntryPoint entryPoint) throws Exception {
        String normalizedAdminPrefix = normalizePrefix(adminPrefix);
        String adminLoginPath = normalizedAdminPrefix + "/auth/login";

        return http.csrf(csrf -> csrf.disable()).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).authorizeHttpRequests(auth -> auth.requestMatchers("/").permitAll().requestMatchers(HttpMethod.GET, "/menus/**").permitAll().requestMatchers(HttpMethod.GET, "/reservations/**").permitAll().requestMatchers(HttpMethod.POST, "/reservations").permitAll().requestMatchers(HttpMethod.POST, "/menus").hasRole("ADMIN").requestMatchers(HttpMethod.PUT, "/menus/**").hasRole("ADMIN").requestMatchers(HttpMethod.DELETE, "/menus/**").hasRole("ADMIN").requestMatchers(HttpMethod.PUT, "/reservations/**").hasRole("ADMIN").requestMatchers(HttpMethod.DELETE, "/reservations/**").hasRole("ADMIN").requestMatchers(adminLoginPath).permitAll().requestMatchers("/admin/**").hasRole("ADMIN").anyRequest().authenticated()).exceptionHandling(ex -> ex.authenticationEntryPoint(entryPoint)).addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class).build();
    }

    @Bean
    public AuthenticationEntryPoint restAuthenticationEntryPoint() {
        return (request, response, authException) -> writeUnauthorized(response);
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails adminUser = User.builder().username(adminUsername).password(passwordEncoder.encode(adminPassword)).roles(adminRole).build();

        return new InMemoryUserDetailsManager(adminUser);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    private String normalizePrefix(String prefix) {
        if (prefix == null || prefix.isBlank()) {
            return "";
        }
        String normalized = prefix.trim();
        if (!normalized.startsWith("/")) {
            normalized = "/" + normalized;
        }
        if (normalized.endsWith("/") && normalized.length() > 1) {
            normalized = normalized.substring(0, normalized.length() - 1);
        }
        return normalized;
    }

    private void writeUnauthorized(HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.getWriter().write("{\"error\":\"Unauthorized\"}");
    }
}
