package main.java.com.payten.ipspgwdummy.exceptions;

public class JwtUserHashIdEmptyException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
    public JwtUserHashIdEmptyException(String message)
    {
        super(message);
        this.message = message;
    }
}
