/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class AppNavigator {
    private static Stage stage;

    public static void setStage(Stage s) { stage = s; }

    public static void load(String fxml) throws Exception {
        stage.setScene(new Scene(FXMLLoader.load(AppNavigator.class.getResource(fxml))));
        stage.centerOnScreen();
    }
}
