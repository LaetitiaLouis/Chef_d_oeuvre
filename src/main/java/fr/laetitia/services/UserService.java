package fr.laetitia.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.laetitia.model.Admin;
import fr.laetitia.repository.AdminRepository;
import fr.laetitia.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @JsonIgnore
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<Admin> admin = adminRepository.findByLogin(login);
        if (admin.isPresent()) {
            return admin.get();
        } else {
            throw new UsernameNotFoundException("Administrateur introuvable");
        }
    }

    public String signin(String login, String password) throws BadCredentialsException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
            return jwtTokenProvider.createToken(login, adminRepository.findByLogin(login).get().getAuthorities());
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Login invalide");
        }
    }

    public Admin signup(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepository.save(admin);
    }
}
