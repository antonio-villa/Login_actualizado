package demo.security_spring.security;


import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTToken {
    public static final long Time_expired=60000;
    @Value("${api.security.secret}")
    private String api_secret;


    public String GenerateToken (Authentication authentication){
        //Date expired = Date.from(LocalDateTime.now().plusHours(1).atOffset(ZoneOffset.of("-05:00")).toInstant());
        Date time= new Date();
        Date Expired = new Date(time.getTime()+Time_expired);
        String token = Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuedAt(time)
                .setExpiration(Expired)
                .signWith(SignatureAlgorithm.HS512,api_secret).compact();
        return token;
    }

    public String ExtraerUsername(String token){
        Claims claim = Jwts.parser()
                .setSigningKey(api_secret)
                .parseClaimsJws(token)
                .getBody();
        return claim.getSubject();
    }
    public Boolean validarToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(api_secret).parseClaimsJws(token);
            return true;
        }  catch (UnsupportedJwtException e) {
            throw new AuthenticationCredentialsNotFoundException("El token no es compatible", e);
        } catch (MalformedJwtException e) {
            throw new AuthenticationCredentialsNotFoundException("El token está mal formado", e);
        } catch (SignatureException e) {
            throw new AuthenticationCredentialsNotFoundException("Firma del token inválida", e);
        } catch (IllegalArgumentException e) {
            throw new AuthenticationCredentialsNotFoundException("El token es nulo o vacío", e);
        }
    }

    /*public Boolean validarToken(String token)  {
        try {
            Jwts.parser()
                    .setSigningKey(api_secret).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            throw new AuthenticationCredentialsNotFoundException("algo esta mal en la firma (contraseña)");
        }
    }*/
}
