package dh.backend.db;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2Connection {

    public static Logger LOGGER =Logger.getLogger(H2Connection.class);

    public static Connection getConnection() throws ClassNotFoundException, SQLException {

        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:~/db_examen_parcial","sa","sa");
    };

}
