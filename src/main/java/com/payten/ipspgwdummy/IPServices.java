package main.java.com.payten.ipspgwdummy;

import org.apache.log4j.Logger;
import org.jpos.core.Configurable;
import org.jpos.core.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class,
		MongoAutoConfiguration.class, MongoRepositoriesAutoConfiguration.class, MongoDataAutoConfiguration.class })
public class IPServices implements Configurable {

	static Logger logger = Logger.getLogger(IPServices.class);

	public void setConfiguration(Configuration cfg) {
		logger.info("SpringApplication starting ...");
		System.out.println("SpringApplication starting ...");
		PgwApiCalls.callbackHost = cfg.get("callbackHost");
		PgwApiCalls.callbackPort = cfg.get("callbackPort", "9095");
		
		
		PgwApiCalls.keyStore = cfg.get("keyStore");
		PgwApiCalls.keyStorePassword= cfg.get("keyStorePassword");
		PgwApiCalls.keyStoreType=cfg.get("keyStoreType");
		PgwApiCalls.keyAlias=cfg.get("keyAlias");
		PgwApiCalls.keyPassword=cfg.get("keyPassword");

		PgwApiCalls.statusCreditTransferApproved = cfg.get("statusCreditTransferApproved", "00");
		PgwApiCalls.statusCreditTransferRejected = cfg.get("statusCreditTransferRejected", "05");
		PgwApiCalls.statusCreditTransferUnknown = cfg.get("statusCreditTransferUnknown", "82");

		PgwApiCalls.checkStatusNumberOfIntent = cfg.getInt("checkStatusNumberOfIntent",3);

		System.setProperty("server.port", PgwApiCalls.callbackPort);

		PgwApiCalls.eCommerceBaseUri = cfg.get("eCommerceBaseUri",null);
		PgwApiCalls.proxy = cfg.getBoolean("proxy",false);
		PgwApiCalls.proxyAddress = cfg.get("proxyAddress","172.17.5.30");
		PgwApiCalls.proxyPort = cfg.getInt("proxyPort",8080);


		PgwApiCalls.generateTokenURL = cfg.get("generateTokenURL",null);
		PgwApiCalls.baseURL = cfg.get("baseURL",null);
		PgwApiCalls.serviceUserId = cfg.get("serviceUserId",null);
		PgwApiCalls.serviceTid = cfg.get("serviceTid",null);

		
		if (cfg.getBoolean("useSSL", false)) {
			System.setProperty("server.ssl.key-store", PgwApiCalls.keyStore );
			System.setProperty("server.ssl.key-store-password", PgwApiCalls.keyStorePassword);
			System.setProperty("server.ssl.key-store-type", PgwApiCalls.keyStoreType);
			if (cfg.get("keyAlias") != null)	
				System.setProperty("server.ssl.key-alias", PgwApiCalls.keyAlias);
			if (cfg.get("keyPassword") != null)	
				System.setProperty("server.ssl.key-password", 	PgwApiCalls.keyPassword);
			
		}

		try {
			String[] args = {};
			SpringApplication.run(IPServices.class, args);
		} catch (Exception e) {
			System.out.println("Publish exception : " + e.getMessage());
			logger.error("Publish exception : " + e.getMessage());
		}
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
}
