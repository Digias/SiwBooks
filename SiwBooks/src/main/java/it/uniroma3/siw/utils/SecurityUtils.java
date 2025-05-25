package it.uniroma3.siw.utils;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Role;
import it.uniroma3.siw.service.CredentialsService;

@Component
public class SecurityUtils {

    public boolean isAuthenticated() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null && !(auth instanceof AnonymousAuthenticationToken);
    }

    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof UserDetails userDetails) {
            return userDetails.getUsername();
        }
        return null;
    }

    public Credentials getCurrentCredentials(CredentialsService credentialsService) {
        String username = getCurrentUsername();
        return username != null ? credentialsService.getCredentials(username) : null;
    }

    public boolean hasRegisteredOrAdminAccess(CredentialsService credentialsService) {
        Credentials credentials = getCurrentCredentials(credentialsService);
        return credentials != null &&
               (credentials.getRole().equals(Role.REGISTERED) ||
                credentials.getRole().equals(Role.ADMIN));
    }
    
    public boolean isAdmin(CredentialsService credentialsService) {
    	Credentials credentials = getCurrentCredentials(credentialsService);
    	return credentials != null && credentials.getRole().equals(Role.ADMIN);
    }
}
