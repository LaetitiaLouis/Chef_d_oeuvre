package fr.laetitia.security;

import fr.laetitia.model.Admin;
import fr.laetitia.service.UserService;
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
import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class JwtTokenProvider {
    //on récupère la secret key dans le fichier properties
    @Value("${security.jwt.token.secret-key}")
    private String secretKey;

    //valeur par défaut
    @Value("${security.jwt.token.expire-length}")
    private long validityInMilliseconds = 36000000; //=10h


    @Autowired
    private UserService userService;

    /**
     * méthode d'initialisation s'exécute avant le constructeur
     * elle encode le code secret en base64 pour la transmission dans le header
     */
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }


    /**
     * méthode qui crée un token avec login
     * @param admin les informations de l'administrateur
     * @return le token sous forme de string
     */
    public String createToken(Admin admin) {
        Claims claims = Jwts.claims().setSubject(admin.getLogin());
        claims.put("auth", admin.getAuthorities().stream().map(GrantedAuthority::getAuthority).filter(Objects::nonNull).collect(Collectors.toList()));
        claims.put("photo", admin.getPhoto());
        claims.put("prenom", admin.getPrenom());
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
     * Obtenir le login
     * @param token le token de l'utilisateur
     * @return le login sous forme de string
     */
    public String extractLogin(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * Retourner l'authentification de l'utilisateur
     * @param token le token de l'utilisateur
     * @return l'authentification sous forme de string
     */
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userService.loadUserByUsername(extractLogin(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    /**
     * Résoudre le Token
     * @param req la requête de type HttpServlet venant du front
     * @return le token sous forme de string
     */
    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); //= nbre lettre Bearer + espace
        }
        return null;
    }

    /**
     *  Vérifier la validation du Token
     * @param token de l'utilisateur
     * @return le token sous forme string
     * @throws Exception si le token est invalide
     */
    public boolean validateToken(String token) throws Exception {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new Exception("Token invalide");
        }
    }
}


