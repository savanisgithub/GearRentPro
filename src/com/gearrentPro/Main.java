/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.gearrentPro;

/**
 *
 * @author User
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.AppNavigator;

public class Main extends Application {

     @Override
     public void start(Stage stage) {
        try {
            AppNavigator.setStage(stage);

            FXMLLoader loader = new FXMLLoader(
                Main.class.getResource("/com/gearrentPro/view/LoginView.fxml")
            );

            Scene scene = new Scene(loader.load());
            stage.setTitle("GearRent Pro");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();

        } catch (Exception e) {
            System.out.println("❌ Failed to load FXML");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}

