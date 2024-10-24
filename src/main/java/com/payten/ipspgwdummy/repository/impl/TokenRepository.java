package main.java.com.payten.ipspgwdummy.repository.impl;


import main.java.com.payten.ipspgwdummy.model.Token;
import main.java.com.payten.ipspgwdummy.repository.ITokenRepository;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;


@Component
public class TokenRepository implements ITokenRepository {

    static Logger logger = Logger.getLogger(TokenRepository.class);

    @Override
    public Token getToken(String tid, String userId) {
        return returnGeneratedToken(tid, userId);
    }

    @Override
    public String validateToken(String tokenValue, String tid) {
        return returnValidationStatusCode(tokenValue, tid);
    }


    private Token returnGeneratedToken(String tid, String userId) {
        Connection conn = null;
        CallableStatement storedProc = null;
        logger.info("Stored procedure [GET_TOKEN]");
        logger.info("tid : " + tid + ", userId: " + userId);
        try {
            conn = com.payten.instant.init.ApplicationInit.databasePoolDS.getConnection();
            String databaseCall;
            String databaseProductName = conn.getMetaData().getDatabaseProductName();
            if (databaseProductName.equals("Oracle")) {
                databaseCall = "{call GET_TOKEN(?,?,?,?,?)}";
            } else {
                databaseCall = "{call [dbo].[GET_TOKEN](?,?,?,?,?)}";
            }
            storedProc = conn.prepareCall(databaseCall);
            storedProc.setString(1, tid);
            storedProc.setString(2, userId);
            storedProc.registerOutParameter(3, Types.VARCHAR);
            storedProc.registerOutParameter(4, Types.VARCHAR);
            storedProc.registerOutParameter(5, Types.VARCHAR);
            storedProc.execute();
            logger.info("Stored procedure [GET_TOKEN] executed");
            Token token = new Token();
            token.setValue(storedProc.getString(3));
            token.setExpiryTime(storedProc.getString(4));
            token.setStatus(storedProc.getString(5));
            logger.info("Token : " + token);
            return token;
        } catch (SQLException e) {
            logger.error("Stored procedure [GET_TOKEN] Exception " + e.getMessage());
            return null;
        } catch (Exception e) {
            logger.error("Stored procedure [GET_TOKEN] Exception" + e.getMessage());
            return null;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    assert storedProc != null;
                    storedProc.close();
                } catch (Exception e) {
                    logger.error("Stored procedure [GET_TOKEN] Release Resources Exception ->" + e.getMessage());
                }
            }
        }
    }

    private String returnValidationStatusCode(String tokenValue, String tid) {
		Connection conn = null;
        CallableStatement storedProc = null;
        logger.info("Stored procedure [CHECK_TOKEN] " + tid + " : " + tokenValue);
        try {

            conn = com.payten.instant.init.ApplicationInit.databasePoolDS.getConnection();

            String databaseCall;
            String databaseProductName = conn.getMetaData().getDatabaseProductName();
            if (databaseProductName.equals("Oracle")) {
                databaseCall = "{call CHECK_TOKEN(?,?,?)}";
            } else {
                databaseCall = "{call [dbo].[CHECK_TOKEN](?,?,?)}";
            }
            storedProc = conn.prepareCall(databaseCall);
            storedProc.setString(1, tid);
            storedProc.setString(2, tokenValue);
            storedProc.registerOutParameter(3, Types.VARCHAR);
            storedProc.execute();
            logger.info("Stored procedure [CHECK_TOKEN] executed : " + storedProc.getString(3));
            return storedProc.getString(3);
        } catch (SQLException e) {
            logger.error("Stored procedure [CHECK_TOKEN] Exception " + e.getMessage());
            return null;
        } catch (Exception e) {
            logger.error("Stored procedure [CHECK_TOKEN] Exception" + e.getMessage());
            return null;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    assert storedProc != null;
                    storedProc.close();
                } catch (Exception e) {
                    logger.error("Stored procedure [CHECK_TOKEN] Release Resources Exception ->" + e.getMessage());
                }
            }
        }
    }


}
