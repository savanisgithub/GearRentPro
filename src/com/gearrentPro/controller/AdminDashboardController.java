/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gearrentPro.controller;

import com.gearrentPro.auth.Session;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class AdminDashboardController {
     @FXML
    void logout() throws Exception {
         Session.clear();
         AnchorPane login =
                FXMLLoader.load(getClass().getResource("/view/LoginView.fxml"));
        Stage stage = (Stage) login.getScene().getWindow();
        stage.setScene(new Scene(login));
    }
}
