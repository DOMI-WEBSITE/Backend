package com.c1848tjavaangular.domi.auth.jwt;

import com.c1848tjavaangular.domi.models.entities.Usuarios;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService {
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthEntryPoint.class);

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration-time}")
    private long jwtExpiration;

    public String getToken(Usuarios user){
        return generateToken(new HashMap<>(), user);
    }

    public String generateToken(Map<String, Object> extraClaims, Usuarios user) {

        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpiration);

        String token = Jwts.builder()
                .claims(extraClaims)
                .subject(user.getEmail())
                .claim("idUsuario", user.getIdUsuarios())
                .issuedAt(new Date())
                .expiration(expireDate)
                .signWith(getKey(),SignatureAlgorithm.HS256)
                .compact();
        //System.out.println("new token :");
        //System.out.println(token);
        //System.out.println(username);
        return token;
    }

    public Key getKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getEmailFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public Integer getIdUsuarioFromToken(String token) {
        return getClaim(token, claims -> claims.get("idUsuario", Integer.class));
    }

    private Claims getAllClaims(String token)
    {
        return Jwts.parser()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T getClaim(String token, Function<Claims,T> claimsResolver)
    {
        final Claims claims=getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(getKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Token mal formado " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("Token no soportado " + e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("Token expirado " + e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("Token vacío " + e.getMessage());
        } catch (SignatureException e) {
            logger.error("Error en al forma " + e.getMessage());
        }
        return false;
    }


}
