package io.augmentedrealms.client.service;

import io.augmentedrealms.client.security.JWTokenAuth;
import io.augmentedrealms.common.database.repository.UserRepository;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    @Value("${jwt.secret}")
    private  String secretKey;

    private UserRepository userRepository;

    public SecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public JWTokenAuth verifyToken(String token) {

        try {
            final Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
            String email = claims.getSubject();
            if (userRepository.existsByEmail(email)) {
                return new JWTokenAuth(email, token);
            }
            return null;
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException
                | SignatureException | IllegalArgumentException e) {
            return null;
        }
    }




}
