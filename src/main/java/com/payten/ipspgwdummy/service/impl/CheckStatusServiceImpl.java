package main.java.com.payten.ipspgwdummy.service.impl;

import main.java.com.payten.ipspgwdummy.model.CTStatus;
import main.java.com.payten.ipspgwdummy.model.CheckStatus;
import main.java.com.payten.ipspgwdummy.service.CheckStatusService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class CheckStatusServiceImpl implements CheckStatusService {

    private static final Logger LOGGER = Logger.getLogger(CheckStatusServiceImpl.class);
    private static final String SUCCESS_STATUS = "00";
    private static final String REJECTED_STATUS = "05";
    private static final String TIMEOUT_STATUS = "82";


    @Override
    public CTStatus checkCTStatus(CheckStatus checkStatus, int alreadyAttempted) throws InterruptedException {

        CTStatus ctStatus = new CTStatus();
        ctStatus.setCreditTransferIdentificator(checkStatus.getCreditTransferIdentificator());
        ctStatus.setTerminalIdentificator(checkStatus.getTerminalIdentificator());


        char firstNumber = checkStatus.getCreditTransferAmount().charAt(0);
        int timeout = Integer.parseInt(checkStatus.getCreditTransferAmount().substring(1, 3)) * 1000;
        String[] parts = checkStatus.getCreditTransferAmount().split("\\.");
        int noOfAttempts = Integer.parseInt(parts[1].substring(0, 1)); // Number after decimal point for attempts
        String attemptCountLog = "Attempt " + alreadyAttempted + " / " + noOfAttempts;


        if (checkStatus.getCreditTransferAmount().length() != 3) {
            LOGGER.error("Credit transfer amount is not correct.");
        }

        //If NoOfAttempts == 1, sleep for timeout duration, set final status and return
        if (noOfAttempts == 1) {
            LOGGER.info(attemptCountLog);
            Thread.sleep(timeout);
            ctStatus.setStatusCode(generateFinalStatus(firstNumber));
            return ctStatus;


        } else if( noOfAttempts == 2){
            if(alreadyAttempted == 1){
                LOGGER.info(attemptCountLog);
                Thread.sleep(60000);
                ctStatus.setStatusCode(REJECTED_STATUS);
                return ctStatus;
            }else if(alreadyAttempted == 2){
                LOGGER.info(attemptCountLog);
                Thread.sleep(timeout);
                ctStatus.setStatusCode(generateFinalStatus(firstNumber));
                return ctStatus;
            }

        }else{
            if(alreadyAttempted == 1 || alreadyAttempted == 2){
                LOGGER.info(attemptCountLog);
                Thread.sleep(60000);
                ctStatus.setStatusCode(REJECTED_STATUS);
                return ctStatus;
            }else if(alreadyAttempted == 3){
                LOGGER.info(attemptCountLog);
                Thread.sleep(timeout);
                ctStatus.setStatusCode(generateFinalStatus(firstNumber));
                return ctStatus;
            }

        }
        //default return in case logic is skipped (This should not happen)
        return ctStatus;
    }

    private String generateFinalStatus(char firstNumber) {
        if (firstNumber == '1') {
            return SUCCESS_STATUS;
        } else if (firstNumber == '2') {
            return REJECTED_STATUS;
        } else {
            return TIMEOUT_STATUS;
        }
    }
}
