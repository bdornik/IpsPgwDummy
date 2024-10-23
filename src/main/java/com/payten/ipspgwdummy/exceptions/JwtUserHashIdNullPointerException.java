package main.java.com.payten.ipspgwdummy.exceptions;

public class JwtUserHashIdNullPointerException extends  Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
    public JwtUserHashIdNullPointerException(String message)
    {
        super(message);
        this.message = message;
    }
}
