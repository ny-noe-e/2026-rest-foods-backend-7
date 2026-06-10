package ch.noseryoung.restaurantbackend.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.security")
public class AdminSecurityProperties {

    private String adminPrefix;
    private String adminUsername;
    private String adminPassword;
    private String adminRole = "ADMIN";

    public String getAdminPrefix() {
        return adminPrefix;
    }

    public void setAdminPrefix(String adminPrefix) {
        this.adminPrefix = adminPrefix;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminRole() {
        return adminRole;
    }

    public void setAdminRole(String adminRole) {
        this.adminRole = adminRole;
    }
}
