package main.java.com.payten.ipspgwdummy.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)

public class JwtUser {

    @NotNull
    @NotEmpty
    @NotBlank
   private String hashedUserId;

    @NotNull
    @NotEmpty
    @NotBlank
    private String tid;

    public String getHashedUserId() {
        return hashedUserId;
    }

    public void setHashedUserId(String hashedUserId) {
        this.hashedUserId = hashedUserId;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }
}
