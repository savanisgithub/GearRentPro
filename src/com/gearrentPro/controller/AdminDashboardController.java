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
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class AdminDashboardController {

    @FXML
    private AnchorPane AnchorPane;

    @FXML
    private MenuItem menuBranches;

    @FXML
    private MenuItem menuCategories;

    @FXML
    public void initialize() {

        // 🔐 HARD ROLE CHECK
        if (Session.getRole() != UserRole.ADMIN) {
            new Alert(Alert.AlertType.ERROR,
                    "Access denied! Admin only.").showAndWait();
            return;
        }

        // Admin can access everything
        menuBranches.setDisable(false);
        menuCategories.setDisable(false);
    }

    @FXML
    void openBranchManagement() throws Exception {
        AnchorPane pane = FXMLLoader.load(
                getClass().getResource("/com/gearrentPro/view/BranchManagement.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(pane));
        stage.setTitle("Branch Management");
        stage.show();
    }

    @FXML
    void openCategoryManagement() throws Exception {
        AnchorPane pane = FXMLLoader.load(
                getClass().getResource("/com/gearrentPro/view/CategoryManagement.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(pane));
        stage.setTitle("Category Management");
        stage.show();
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
