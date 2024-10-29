package main.java.com.payten.ipspgwdummy.controller;

import main.java.com.payten.ipspgwdummy.exceptions.DelimiterNotCorrectException;
import main.java.com.payten.ipspgwdummy.model.CTStatus;
import main.java.com.payten.ipspgwdummy.model.CheckStatus;
import main.java.com.payten.ipspgwdummy.service.CheckStatusService;
import org.apache.log4j.Logger;
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
    public ResponseEntity<CTStatus> checkCTStatus(@RequestBody CheckStatus checkStatus) throws InterruptedException, DelimiterNotCorrectException {
        LOGGER.info("checkCTStatus request: " + checkStatus);
        CTStatus response = checkStatusService.checkStatus(checkStatus);
        LOGGER.info("checkCTStatus response: " + response);
        return ResponseEntity.ok(response);
    }
}