package main.java.com.payten.ipspgwdummy.service;

import main.java.com.payten.ipspgwdummy.model.CTStatus;
import main.java.com.payten.ipspgwdummy.model.CheckStatus;

@FunctionalInterface
public interface CheckStatusService {
    CTStatus checkCTStatus(CheckStatus checkStatus, int alreadyAttempted) throws InterruptedException;
}
