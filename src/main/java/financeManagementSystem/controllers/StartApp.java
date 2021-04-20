package financeManagementSystem.controllers;

import financeManagementSystem.model.FinanceManagementSystem;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class StartApp extends Application {

    public static FinanceManagementSystem fms = new FinanceManagementSystem();

    @Override
    public void start(Stage primaryStage) throws Exception{
        URL url = new File("src/main/java/financeManagementSystem/controllers/LoginPage.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);

        //FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginPage.fxml"));
        Parent root = loader.load();

        LoginPage loginPage = loader.getController();
        loginPage.setFms(fms);

        primaryStage.setTitle("Finance Management System");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

