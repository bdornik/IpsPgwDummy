package main.java.com.payten.ipspgwdummy.controller;

import main.java.com.payten.ipspgwdummy.model.CTStatus;
import main.java.com.payten.ipspgwdummy.model.CheckStatus;
import main.java.com.payten.ipspgwdummy.service.CheckStatusService;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ips/v1")
public class CheckStatusController {

    private static final Logger LOGGER = Logger.getLogger(CheckStatusController.class);
    private final CheckStatusService checkStatusService;

    public CheckStatusController(CheckStatusService checkStatusService) {
        this.checkStatusService = checkStatusService;
    }

    @PostMapping("/checkCTStatus")
    public ResponseEntity<CTStatus> checkCTStatus(@RequestBody CheckStatus checkStatus) throws InterruptedException {
        LOGGER.info("Check status received: " + checkStatus);
        return handleRetry(checkStatus, 1);
    }

    private ResponseEntity<CTStatus> handleRetry(CheckStatus checkStatus, int alreadyAttempted) throws InterruptedException {
        LOGGER.info("Inside of handleRetry...");

        // Process one retry attempt and return CTStatus
        CTStatus ctStatus = checkStatusService.checkCTStatus(checkStatus, alreadyAttempted);

        // After returning the CTStatus, check if more attempts are needed
        String[] parts = checkStatus.getCreditTransferAmount().split("\\.");
        int noOfAttempts = Integer.parseInt(parts[1].substring(0, 1));

        // If more attempts are needed, continue in the background
        if (alreadyAttempted < noOfAttempts) {
            new Thread(() -> {
                LOGGER.info("Attempting to retry...");
                try {
                    handleRetry(checkStatus, alreadyAttempted + 1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    LOGGER.error(e.getMessage());
                }

            }).start();
        }

        // Return the current state of CTStatus
        return ResponseEntity.status(HttpStatus.OK).body(ctStatus);
    }

}
