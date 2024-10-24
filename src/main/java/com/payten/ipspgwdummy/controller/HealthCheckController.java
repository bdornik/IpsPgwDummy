package main.java.com.payten.ipspgwdummy.controller;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin("*")
public class HealthCheckController {

	static Logger logger = Logger.getLogger(HealthCheckController.class);

	@GetMapping("/healthCheck")
	@ResponseStatus(value = HttpStatus.OK)
	public String getMerchantDetails() {

		logger.info("/healthCheck");
		return "Good Health";
	}

}