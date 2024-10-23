package main.java.com.payten.ipspgwdummy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class IpspgwdummyApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(IpspgwdummyApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(IpspgwdummyApplication.class);
	}

}
