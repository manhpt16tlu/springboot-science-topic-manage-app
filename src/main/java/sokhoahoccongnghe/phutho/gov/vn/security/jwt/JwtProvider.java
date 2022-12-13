package sokhoahoccongnghe.phutho.gov.vn.security.jwt;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import sokhoahoccongnghe.phutho.gov.vn.security.usersecure.UserDetailsImpl;

import java.util.Date;

@Component
public class JwtProvider {
    //    @Autowired
//    private Environment env;
    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);
    @Value("${quanlydetai.app.jwt.secretkey}")
    private String jwtSecretKey;
    @Value("${quanlydetai.app.jwt.expired-time}")
    private int jwtExpiredTime;

    //    private String jwtExpiredTime = env.getProperty("quanlydetai.app.jwt.expired-time"); //có thể dùng env
    public String generateToken(Authentication authentication) {
        //user sau khi được xác thực
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date()) // thời điểm phát hành
                .setExpiration(new Date(new Date().getTime() + jwtExpiredTime))
                .signWith(SignatureAlgorithm.HS512, jwtSecretKey)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

    public String getUserNameFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token).getBody().getSubject();
    }
}
