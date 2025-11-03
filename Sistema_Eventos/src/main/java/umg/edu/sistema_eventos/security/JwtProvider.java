package umg.edu.sistema_eventos.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    // Clave secreta para firmar el token (debe ser fuerte)
    @Value("${jwt.secret:una-clave-super-secreta-y-larga-de-mas-de-64-caracteres-para-el-sistema-de-eventos-umg-2025-v1}") // <--- AUMENTAR LONGITUD
    private String secret;

    // Tiempo de expiración del token (ej: 1 hora)
    @Value("${jwt.expiration:3600000}")
    private long expiration;

    private Key key;

    // Se ejecuta al iniciar la aplicación para crear la clave de firma
    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    /** Genera el token JWT a partir de la información de autenticación. */
    public String generateToken(Authentication authentication) {
        // Obtenemos el usuario que se acaba de autenticar
        User principal = (User) authentication.getPrincipal();

        // Obtenemos los roles del usuario para incluirlos en el token
        String roles = principal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .setSubject(principal.getUsername())
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    /** Obtiene el nombre de usuario desde el token. */
    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /** Valida la estructura y la firma del token. */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Token mal formado: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("Token expirado: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("Token no soportado: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("Token vacío o nulo: {}", e.getMessage());
        } catch (SignatureException e) {
            logger.error("Fallo en la firma del token: {}", e.getMessage());
        }
        return false;
    }
}