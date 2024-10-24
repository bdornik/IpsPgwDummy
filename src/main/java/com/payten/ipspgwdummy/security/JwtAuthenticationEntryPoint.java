package main.java.com.payten.ipspgwdummy.security;


import org.apache.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
	static Logger logger = Logger.getLogger(JwtAuthenticationEntryPoint.class);
	
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException
    {
    	logger.info("JwtAuthenticationEntryPoint commence");
    	httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,"User unauthorized");
    	  
    }

}
