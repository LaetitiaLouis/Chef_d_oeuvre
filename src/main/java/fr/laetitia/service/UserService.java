package fr.laetitia.service;

import fr.laetitia.model.Admin;
import fr.laetitia.repository.AdminRepository;
import fr.laetitia.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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


    /**
     * on redéfinit la méthode loadByUsername pour récuperer un admin par son identifiant
     *
     * @param login le login de l'utilisateur
     * @return un utilisateur
     * @throws UsernameNotFoundException une erreur si administrateur introuvable
     */
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<Admin> admin = adminRepository.findByLogin(login);
        if (admin.isPresent()) {
            return admin.get();
        } else {
            throw new UsernameNotFoundException("Administrateur introuvable");
        }
    }


    /**
     * Recupère l'identifiant et le mot de passe saisi, on le compare à notre base de données
     * et si la combinaison est correcte alors on lui attribue un token
     *
     * @param login    de l'utilisateur
     * @param password de l'utilisateur
     * @return le token
     * @throws BadCredentialsException une erreur si le login est invalide
     */
    public String signin(String login, String password) throws BadCredentialsException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
            Admin admin = adminRepository.findByLogin(login).get();
            return jwtTokenProvider.createToken(admin);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Login invalide");
        }
    }

    /**
     * encode le mot de passe
     */
    public Admin signup(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        //à la creation d'un admin le role "ADMIN" lui est attribué par defaut
        admin.setRole("ADMIN");
        return adminRepository.save(admin);
    }
}
