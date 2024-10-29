package main.java.com.payten.ipspgwdummy.model;

public class SessionToken {

	    private String sessionToken;
	    private String tokenExpiriyTime;

	public SessionToken() {
	}

	public SessionToken(String sessionToken, String tokenExpiriyTime) {
		this.sessionToken = sessionToken;
		this.tokenExpiriyTime = tokenExpiriyTime;
	}

	public String getSessionToken() {
	        return sessionToken;
	    }

	    public String getTokenExpiriyTime() {
	        return tokenExpiriyTime;
	    }

	    public void setTokenExpiriyTime(String tokenExpiriyTime) {
	        this.tokenExpiriyTime = tokenExpiriyTime;
	    }

	    public void setSessionToken(String sessionToken) {
	        this.sessionToken = sessionToken;
	    }

	    @Override
	    public String toString() {
	        return "SessionToken{" +
	                "sessionToken='" + sessionToken + '\'' +
	                ", tokenExpiriyTime='" + tokenExpiriyTime + '\'' +
	                '}';
	    }
	}
