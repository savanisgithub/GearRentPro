/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gearrentPro.controller;

import com.gearrentPro.auth.Session;
import com.gearrentPro.entity.UserRole;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class StaffDashboardController {
    @FXML
    private AnchorPane AnchorPane;

//    @FXML
//    private MenuItem menuCategories;
    public void initialize() {
        if (Session.getRole() != UserRole.STAFF) {
            new Alert(Alert.AlertType.ERROR,
                    "Access denied! staff manager only.")
                    .showAndWait();
            return;
        }
//        menuCategories.setDisable(false);
    }


    @FXML
    void openEquipmentManagement() throws Exception {
        AnchorPane pane = FXMLLoader.load(
                getClass().getResource("/com/gearrentPro/view/EquipmentManagement.fxml"));

        Stage stage = new Stage();
        stage.setTitle("Equipment Management");
        stage.setScene(new Scene(pane));
        stage.show();
    }

    @FXML
    void logout() {
        try {
            Session.clear();
            AnchorPane login = FXMLLoader.load(
                    getClass().getResource("/com/gearrentPro/view/LoginView.fxml"));
            Stage stage = (Stage) AnchorPane.getScene().getWindow();
            stage.setScene(new Scene(login));
            stage.centerOnScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
