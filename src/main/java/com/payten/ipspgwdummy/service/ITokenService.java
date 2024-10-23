package main.java.com.payten.ipspgwdummy.service;


import main.java.com.payten.ipspgwdummy.model.Token;
import org.springframework.stereotype.Service;

@Service
public interface ITokenService {
    Token getToken(String tid, String userId);
    String  validateToken(String tokenValue,String tid);

}
