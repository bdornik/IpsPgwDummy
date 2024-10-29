package main.java.com.payten.ipspgwdummy.exceptions;

public class DelimiterNotCorrectException extends Exception{
    String message;

    public DelimiterNotCorrectException(String message) {
        super(message);
        this.message = message;
    }
}
