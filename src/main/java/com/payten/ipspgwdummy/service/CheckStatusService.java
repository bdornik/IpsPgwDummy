package main.java.com.payten.ipspgwdummy.service;

import main.java.com.payten.ipspgwdummy.exceptions.DelimiterNotCorrectException;
import main.java.com.payten.ipspgwdummy.model.CTStatus;
import main.java.com.payten.ipspgwdummy.model.CheckStatus;

@FunctionalInterface
public interface CheckStatusService {
   public CTStatus checkStatus(CheckStatus checkStatus) throws InterruptedException, DelimiterNotCorrectException;
}
