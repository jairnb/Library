package com.example.library.model.database;

public class DatabaseFactory {
    public static Database getDatabase(String database){
        Database db = null;
        if ("mysql".equals(database)) {
            db = new MySQLDatabase();
        }

        return db;
    }
}
