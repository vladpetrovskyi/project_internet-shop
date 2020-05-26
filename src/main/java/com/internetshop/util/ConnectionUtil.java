package com.internetshop.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionUtil {

    private static final Logger LOGGER = LogManager.getLogger(ConnectionUtil.class);

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            LOGGER.info("DataBase driver is registered.");
        } catch (ClassNotFoundException e) {
            LOGGER.error("Could not find MySQL driver.", e);
            throw new RuntimeException("Could not find MySQL driver.", e);
        }
    }

    public static Connection getConnection() {
        Properties dbProperties = new Properties();
        dbProperties.put("user", "b410d4eed3bd25");
        dbProperties.put("password", "17b0c0f1");
        String url = "jdbc:mysql://b410d4eed3bd25:17b0c0f1@eu-cdbr-west-03.cleardb.net"
                + "/heroku_24cd0fb75b0a4c2?reconnect=true";
        try {
            Connection connection = DriverManager.getConnection(url, dbProperties);
            LOGGER.info("Connection to DB is established.");
            return connection;
        } catch (SQLException ex) {
            LOGGER.error("Can't establish the connection to DB.", ex);
            throw new RuntimeException("Can't establish the connection to DB.", ex);
        }
    }
}
