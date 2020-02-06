package com.company;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static Stage PRIMARYSTAGE;
    public static Scene MAIN;
    public static FileClient FILEGAMER;
    public static int HEIGHT = 800;
    public static int WIDTH = 1400;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("TestforFileSendingandReceiving.fxml"));
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        //primaryStage.setFullScreen(true);
        primaryStage.show();
        System.out.println("Basic Welcome Screen ");
    }

    public static void main(String[] args) throws IOException {
        FILEGAMER = new FileClient("localhost", 5558,"FileClientsSocket");
        System.out.println("Come back to main");
        launch(args);
    }
}
