package main.java.com.payten.ipspgwdummy.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import main.java.com.payten.ipspgwdummy.model.JwtUser;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenGenerator {

    public String generate(JwtUser jwtUser){
        Claims claims = Jwts.claims().setSubject(jwtUser.getHashedUserId());
        claims.put("hashedUserId",String.valueOf(jwtUser.getHashedUserId()));
        return Jwts.builder()
               .setClaims(claims)
               .signWith(SignatureAlgorithm.HS512,"nemanja")
               .compact();
    }
}
