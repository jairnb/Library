package com.example.library;

import com.example.library.model.database.Database;
import com.example.library.model.database.DatabaseFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.Connection;
import java.io.IOException;

import io.github.cdimascio.dotenv.Dotenv;

import javax.xml.transform.Result;

public class Main extends Application {
    public static Dotenv dotenv = Dotenv.load();

    public static final Database database = DatabaseFactory.getDatabase(Main.dotenv.get("DATABASE_PROVIDER"));
    public static final Connection connection = database.connect();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/member/addUpdateMember.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 920, 700);
        stage.setTitle("Library!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}