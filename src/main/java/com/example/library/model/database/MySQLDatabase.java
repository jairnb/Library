package com.example.library.model.database;

import com.example.library.Main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLDatabase implements Database{
    private final String PASSWORD = Main.dotenv.get("DATABASE_PASSWORD");
    private final String DB_NAME = Main.dotenv.get("DATABASE_NAME");
    private final String USER_NAME = Main.dotenv.get("DATABASE_USER");

    private Connection connection;

    @Override
    public Connection connect() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/"+DB_NAME, USER_NAME,PASSWORD);
            return this.connection;
        } catch (SQLException ex) {
            Logger.getLogger(MySQLDatabase.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public void disconnect(Connection conn) {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
