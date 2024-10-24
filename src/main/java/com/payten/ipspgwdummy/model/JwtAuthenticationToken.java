package main.java.com.payten.ipspgwdummy.model;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tokenValue;
    private String tid;

    public JwtAuthenticationToken(String token, String tid) {
        super(null, null);
        this.tokenValue = token;
        this.tid = tid;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public String toString() {
        return "JwtAuthenticationToken{" +
                "tokenValue='" + tokenValue + '\'' +
                ", tid='" + tid + '\'' +
                '}';
    }
}
