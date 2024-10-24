package main.java.com.payten.ipspgwdummy.model;

import org.springframework.stereotype.Component;

public class CTStatus {

    private String creditTransferIdentificator;
    private String terminalIdentificator;
    private String statusCode;

    public String getCreditTransferIdentificator() {
        return creditTransferIdentificator;
    }

    public void setCreditTransferIdentificator(String creditTransferIdentificator) {
        this.creditTransferIdentificator = creditTransferIdentificator;
    }

    public String getTerminalIdentificator() {
        return terminalIdentificator;
    }

    public void setTerminalIdentificator(String terminalIdentificator) {
        this.terminalIdentificator = terminalIdentificator;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return "CTStatus{" +
                "creditTransferIdentificator='" + creditTransferIdentificator + '\'' +
                ", terminalIdentificator='" + terminalIdentificator + '\'' +
                ", statusCode='" + statusCode + '\'' +
                '}';
    }
}
