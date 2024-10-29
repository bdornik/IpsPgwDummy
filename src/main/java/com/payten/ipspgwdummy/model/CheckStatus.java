package main.java.com.payten.ipspgwdummy.model;

public class CheckStatus {

    private String creditTransferIdentificator;
    private String terminalIdentificator;
    private String creditTransferAmount;
    private String stan;


    public CheckStatus(String creditTransferIdentificator, String terminalIdentificator, String creditTransferAmount, String stan) {
        this.creditTransferIdentificator = creditTransferIdentificator;
        this.terminalIdentificator = terminalIdentificator;
        this.creditTransferAmount = creditTransferAmount;
        this.stan = stan;
    }

    public CheckStatus() {
    }

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

    public String getCreditTransferAmount() {
        return creditTransferAmount;
    }

    public void setCreditTransferAmount(String creditTransferAmount) {
        this.creditTransferAmount = creditTransferAmount;
    }

    public String getStan() {
        return stan;
    }

    public void setStan(String stan) {
        this.stan = stan;
    }

    @Override
    public String toString() {
        return "CheckStatus{" +
                "creditTransferIdentificator='" + creditTransferIdentificator + '\'' +
                ", terminalIdentificator='" + terminalIdentificator + '\'' +
                ", creditTransferAmount='" + creditTransferAmount + '\'' +
                ", stan='" + stan + '\'' +
                '}';
    }
}
