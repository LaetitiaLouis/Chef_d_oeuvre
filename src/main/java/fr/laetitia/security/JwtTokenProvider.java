package fr.laetitia.security;

import fr.laetitia.model.Admin;
import fr.laetitia.services.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class JwtTokenProvider {
    //on récupère le secret dans le fichier properties
    @Value("${security.jwt.token.secret-key}")
    private String secretKey;

    //valeur par défaut
    @Value("${security.jwt.token.expire-length}")
    private long validityInMilliseconds = 36000000; //=10h


    @Autowired
    private UserService userService;

    /**
     * méthode d'initialisation s'exécute avant le constructeur
     * elle encode le code secret en base64pour la transmission dans le header
     */
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    /**
     * méthode qui créer un token avec login
     * "sub" pour login
     * "auth" pour roles
     * "iat" pour date du jour
     * "exp" pour date du jour + validityTime
     */
    public String createToken(Admin admin) {//mettre adin en parametre et enlever login..
        Claims claims = Jwts.claims().setSubject(admin.getLogin());
        claims.put("auth", admin.getAuthorities().stream().map(s -> s.getAuthority()).filter(Objects::nonNull).collect(Collectors.toList()));
        claims.put("photo", admin.getPhoto());
        claims.put("prenom",admin.getPrenom());
//        claims.put(""), admin.isCompteValide();
        claims.put("presentation", admin.getPresentation());

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    /**
     * Méthode qui obtient le  login
     * @param token
     */
    public String extractLogin(String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * méthode qui retourne l'authentification de l'utilisateur
     */
    public Authentication getAuthentication(String token){
        UserDetails userDetails = userService.loadUserByUsername(extractLogin(token));
        return new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities());
    }

    /**
     * Méthode qui resout
     */
    public String resolveToken(HttpServletRequest req){
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7); //= nbre lettre Bearer + espace
        }
        return null;
    }

    /**
     * méthode sui vérifie la validation
     */
    public boolean validateToken(String token) throws Exception {
        try{
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch(JwtException | IllegalArgumentException e){
            throw new Exception("Token invalide");
        }
    }
}


