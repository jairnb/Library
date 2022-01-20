package com.example.library;

import com.example.library.model.database.Database;
import com.example.library.model.database.DatabaseFactory;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;

public class StaticHelpers {
    public static Dotenv dotenv = Dotenv.load();
    public static final Database database = DatabaseFactory.getDatabase(dotenv.get("DATABASE_PROVIDER"));
    public static final Connection connection = database.connect();
}
