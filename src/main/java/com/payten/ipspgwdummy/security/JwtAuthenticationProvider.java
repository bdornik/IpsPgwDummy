package main.java.com.payten.ipspgwdummy.security;


import main.java.com.payten.ipspgwdummy.model.JwtAuthenticationToken;
import main.java.com.payten.ipspgwdummy.model.JwtUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private JwtValidatior jwtValidatior;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) usernamePasswordAuthenticationToken;
        String tokenValue = jwtAuthenticationToken.getTokenValue();
        String tid = jwtAuthenticationToken.getTid();
        String status = (String) jwtValidatior.validate(tokenValue, tid);
        if (!status.trim().equalsIgnoreCase("00")) {

            throw new AuthenticationServiceException("Authentication failed! JWT/TID not valid!");

        }
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
        return new JwtUserDetails(tokenValue, grantedAuthorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {

        return (JwtAuthenticationToken.class.isAssignableFrom(aClass));
    }
}
