/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gearrentPro.controller;

import com.gearrentPro.auth.Session;
import com.gearrentPro.entity.SystemUser;
import com.gearrentPro.entity.UserRole;
import com.gearrentPro.service.AuthService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class LoginController {

    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private AnchorPane ancLogin;

    private final AuthService authService = new AuthService();

    @FXML
    void loginOnAction(ActionEvent event) {

        try {
            SystemUser user = authService.login(
                    txtUsername.getText(),
                    txtPassword.getText()
            );

            Session.set(user.getUsername(), user.getRole());

            AnchorPane dashboard;

            if (user.getRole() == UserRole.ADMIN) {
                dashboard = FXMLLoader.load(
                        getClass().getResource("/com/gearrentPro/view/AdminDashboard.fxml"));

            } else if (user.getRole() == UserRole.BRANCH_MANAGER) {
                dashboard = FXMLLoader.load(
                        getClass().getResource("/com/gearrentPro/view/ManagerDashboard.fxml"));

            } else {
                dashboard = FXMLLoader.load(
                        getClass().getResource("/com/gearrentPro/view/StaffDashboard.fxml"));
            }

            Stage stage = (Stage) ancLogin.getScene().getWindow();
            stage.setScene(new Scene(dashboard));
            stage.centerOnScreen();

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            e.printStackTrace();
        }
    }
}
