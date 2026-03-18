package ra.security.jwt.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@Slf4j
public class JwtService {
    @Value("${jwt.secret-key}")
    private String secretKey;
    @Value("${jwt.expired}")
    private long expired;
    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }
    // sinh token
    public String generateAccessToken(String username){ // 24h
        Date now = new Date();
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime()+60))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }
    public String generateRefreshToken(String username){ // thời hạn lâu hơn 7 ngày
        Date now = new Date();
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime()+expired*7))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }
    // xác minh token
    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(key()).build().parse(token);
            return true;
        }catch (MalformedJwtException e){
            log.error("Invalid token ",e.getMessage());
        }catch (UnsupportedJwtException e){
            log.error("Unsupported token ",e.getMessage());
        }catch (IllegalArgumentException e){
            log.error("Jwt key string invalid ",e.getMessage());
        }
        return false;
    }
    // giải mã token
    public String getUserNameFromToken(String token){
        return Jwts.parser().setSigningKey(key()).build().parseClaimsJws(token).getBody().getSubject();
    }


}
