package main.java.com.payten.ipspgwdummy.service.impl;

import main.java.com.payten.ipspgwdummy.PgwApiCalls;
import main.java.com.payten.ipspgwdummy.exceptions.DelimiterNotCorrectException;
import main.java.com.payten.ipspgwdummy.model.CTStatus;
import main.java.com.payten.ipspgwdummy.model.CheckStatus;
import main.java.com.payten.ipspgwdummy.service.CheckStatusService;
import org.apache.log4j.Logger;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CheckStatusServiceImpl implements CheckStatusService {

    private static final Logger LOGGER = Logger.getLogger(CheckStatusServiceImpl.class);
    private static final String SUCCESS_STATUS = PgwApiCalls.statusCreditTransferApproved;
    private static final String REJECTED_STATUS = PgwApiCalls.statusCreditTransferRejected;
    private static final String TIMEOUT_STATUS = PgwApiCalls.statusCreditTransferUnknown;

    private final Map<String, Integer> attemptsMap = new ConcurrentHashMap<>();

    @Override
    public CTStatus checkStatus(CheckStatus checkStatus) throws InterruptedException, DelimiterNotCorrectException {

        String creditTransferId = checkStatus.getCreditTransferIdentificator();

        attemptsMap.putIfAbsent(creditTransferId, 1);
        int lastTimeout;
        int totalAttempts;
        String[] amountParts = checkStatus.getCreditTransferAmount().split(",");
        try {
            lastTimeout = Integer.parseInt(amountParts[0].substring(1, 3)) * 1000;
            totalAttempts = Integer.parseInt(amountParts[1].substring(0, 1));
        } catch (Exception e) {
            throw new DelimiterNotCorrectException("Delimiter must be comma...");
        }

        char statusNumber = checkStatus.getCreditTransferAmount().charAt(0);
        int currentAttempt = attemptsMap.get(creditTransferId);


        CTStatus ctStatus = new CTStatus();
        ctStatus.setCreditTransferIdentificator(creditTransferId);
        ctStatus.setTerminalIdentificator(checkStatus.getTerminalIdentificator());

        if (statusNumber == '3') {
            ctStatus.setStatusCode(TIMEOUT_STATUS);
            attemptsMap.put(creditTransferId, currentAttempt + 1);
            Thread.sleep(60000);
            return ctStatus;
        }

        try {
            if (currentAttempt < totalAttempts) {
                LOGGER.info("Attempt + " + currentAttempt + " / " + totalAttempts);
                // Intermediate attempts: set REJECTED status and sleep for 60s
                ctStatus.setStatusCode(TIMEOUT_STATUS);
                Thread.sleep(60000);
                attemptsMap.put(creditTransferId, currentAttempt + 1);
                return ctStatus;
            } else {
                LOGGER.info("Attempt + " + currentAttempt + " / " + totalAttempts);
                // Final attempt: apply last timeout and final status
                ctStatus.setStatusCode(generateFinalStatus(statusNumber));
                Thread.sleep(lastTimeout);
                attemptsMap.remove(creditTransferId); // Clean up map
                return ctStatus;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return ctStatus;
    }

    private String generateFinalStatus(char num1) {
        switch (num1) {
            case '1':
                return SUCCESS_STATUS;
            case '2':
                return REJECTED_STATUS;
            default:
                return TIMEOUT_STATUS;
        }
    }


}
