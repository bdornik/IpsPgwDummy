package main.java.com.payten.ipspgwdummy.repository;


import main.java.com.payten.ipspgwdummy.model.Token;
import org.springframework.stereotype.Repository;

@Repository
public interface ITokenRepository {

    Token getToken(String tid, String userId);
    String  validateToken(String tokenValue,String tid);
}
