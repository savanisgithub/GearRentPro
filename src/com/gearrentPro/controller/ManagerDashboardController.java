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
public class ManagerDashboardController {
    public void initialize() {
        if (Session.getRole() != UserRole.BRANCH_MANAGER) {
            new Alert(Alert.AlertType.ERROR,
                    "Access denied! Branch Manager only.")
                    .showAndWait();
            logout();
        }
    }

    @FXML
    void openCategoryManagement() throws Exception {
        AnchorPane pane = FXMLLoader.load(
                getClass().getResource("/com/gearrentPro/view/CategoryManagement.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(pane));
        stage.show();
    }

    @FXML
    void logout() {
        try {
            Session.clear();
            AnchorPane login = FXMLLoader.load(
                    getClass().getResource("/com/gearrentPro/view/LoginView.fxml"));
            Stage stage = (Stage) login.getScene().getWindow();
            stage.setScene(new Scene(login));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
