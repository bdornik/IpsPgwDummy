package main.java.com.payten.ipspgwdummy;

import org.apache.log4j.Logger;


public class PgwApiCalls {
	static Logger logger = Logger.getLogger(PgwApiCalls.class);
	public static String callbackHost;
	public static String callbackPort;
	public static String newline = System.getProperty("line.separator");

	public static String statusCreditTransferApproved;
	public static String statusCreditTransferRejected;
	public static String statusCreditTransferUnknown;

	public static int checkStatusNumberOfIntent;

	public static String keyStore;
	public static String keyStorePassword;
	public static String keyStoreType;
	public static String keyAlias;
	public static String keyPassword;


	public static String eCommerceBaseUri;
	public static boolean proxy;
	public static String proxyAddress;
	public static int proxyPort;
	public static String generateTokenURL;
	public static String baseURL;
	public static String serviceUserId;
	public static String serviceTid;

}
