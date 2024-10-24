package main.java.com.payten.ipspgwdummy.security;


import main.java.com.payten.ipspgwdummy.service.impl.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JwtValidatior {

    @Autowired
    TokenService tokenService;

    public String validate(String tokenValue, String tid) {
        String status = tokenService.validateToken(tokenValue, tid);
        return status;
    }
}
