package main.java.com.payten.ipspgwdummy.service.impl;


import main.java.com.payten.ipspgwdummy.model.Token;
import main.java.com.payten.ipspgwdummy.repository.ITokenRepository;
import main.java.com.payten.ipspgwdummy.service.ITokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TokenService implements ITokenService {


    ITokenRepository tokenRepository;

    public TokenService(ITokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public Token getToken(String tid, String userId) {
        return tokenRepository.getToken(tid,userId);
    }

    @Override
    public String validateToken(String tokenValue,String tid) {
        return  tokenRepository.validateToken(tokenValue,tid);
    }
}
