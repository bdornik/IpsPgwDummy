package main.java.com.payten.ipspgwdummy.security;

import main.java.com.payten.ipspgwdummy.model.JwtAuthenticationToken;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String TID_HEADER = "Terminal-Identification";
	static Logger logger = Logger.getLogger(JwtAuthenticationTokenFilter.class);

	public JwtAuthenticationTokenFilter() {
		super("/**");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws AuthenticationException{
		logger.info("attemptAuthentication method");
		String authorizationHeader = httpServletRequest.getHeader(HEADER_STRING);
		String tidValue = httpServletRequest.getHeader(TID_HEADER);
		if (authorizationHeader == null || !authorizationHeader.startsWith(TOKEN_PREFIX)) {

			throw new RuntimeException("JWT token is missing!");

		}
		String tokenValue = authorizationHeader.replace(TOKEN_PREFIX, "");
		JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(tokenValue, tidValue);
		return getAuthenticationManager().authenticate(jwtAuthenticationToken);

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		super.successfulAuthentication(request, response, chain, authResult);
		chain.doFilter(request, response);
	}

}
