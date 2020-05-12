package vlad.petrovskyi.internetshop.util;

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
            throw new RuntimeException("Could not find MySQL driver", e);
        }
    }

    public static Connection getConnection() {
        Properties dbProperties = new Properties();
        dbProperties.put("user", "root");
        dbProperties.put("password", "********");

        String url = "jdbc:mysql://localhost:3306/internet_shop?serverTimezone=UTC";
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
