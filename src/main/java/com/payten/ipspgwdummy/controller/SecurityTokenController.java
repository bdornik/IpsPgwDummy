package main.java.com.payten.ipspgwdummy.controller;


import main.java.com.payten.ipspgwdummy.exceptions.JwtUserHashIdEmptyException;
import main.java.com.payten.ipspgwdummy.exceptions.JwtUserHashIdNullPointerException;
import main.java.com.payten.ipspgwdummy.model.JwtUser;
import main.java.com.payten.ipspgwdummy.model.JwtUserData;
import main.java.com.payten.ipspgwdummy.model.SessionToken;
import main.java.com.payten.ipspgwdummy.model.Token;
import main.java.com.payten.ipspgwdummy.service.ITokenService;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class SecurityTokenController {

    static Logger logger = Logger.getLogger(SecurityTokenController.class);


    ITokenService tokenService;

    public SecurityTokenController(ITokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/res/v1/generateToken")
    public ResponseEntity<SessionToken> generate(@RequestBody final JwtUserData jwtUser) throws JwtUserHashIdEmptyException, JwtUserHashIdNullPointerException {
        if (jwtUser.getUserId() == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        if (jwtUser.getUserId().isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        else {
            logger.info("Generating token ....");
            Token token = tokenService.getToken(jwtUser.getTid(), jwtUser.getUserId());
            SessionToken sessionToken = new SessionToken();
            if (token != null) {
                logger.info("Token generate status : " + token.getStatus());
                if (token.getStatus() != null && token.getStatus().equals("00")) {
                    sessionToken.setSessionToken(token.getValue());
                    sessionToken.setTokenExpiriyTime(token.getExpiryTime());
                    return new ResponseEntity<>(sessionToken, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(sessionToken, HttpStatus.UNAUTHORIZED);
                }
            } else {
                return new ResponseEntity<>(sessionToken, HttpStatus.SERVICE_UNAVAILABLE);
            }

        }
    }

    @PostMapping("/generate")
    public ResponseEntity<Token> generate(@RequestBody final JwtUser jwtUser) throws JwtUserHashIdEmptyException, JwtUserHashIdNullPointerException {

        if (jwtUser.getHashedUserId() == null) {
            throw new JwtUserHashIdNullPointerException("User id cannot be null");
        }
        if (jwtUser.getHashedUserId().isEmpty()) {
            throw new JwtUserHashIdEmptyException("User id cannot be empty");
        }
        if (jwtUser.getTid() == null) {
            throw new JwtUserHashIdNullPointerException("Terminal id cannot be null");
        } else {
            Token token = tokenService.getToken(jwtUser.getTid(), jwtUser.getHashedUserId());
            return new ResponseEntity<>(token, HttpStatus.OK);
        }
    }

}
