package main.java.com.payten.ipspgwdummy.model;


public class Token {

    private String value;
    private String status;
    private String expiryTime;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExpiryTime() {
		return expiryTime;
	}

	public void setExpiryTime(String expiryTime) {
		this.expiryTime = expiryTime;
	}

	@Override
	public String toString() {
		return "Token [value=" + value + ", status=" + status + ", expiryTime=" + expiryTime + ", getValue()="
				+ getValue() + ", getStatus()=" + getStatus() + ", getExpiryTime()=" + getExpiryTime() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
}
