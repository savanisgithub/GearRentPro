/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gearrentPro.controller;

import com.gearrentPro.auth.Session;
import com.gearrentPro.service.BranchService;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import com.gearrentPro.entity.Branch;
import com.gearrentPro.entity.UserRole;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author User
 */
public class BranchController {

    @FXML
    private TableView<Branch> tblBranches;
    @FXML
    private TableColumn<Branch, String> colCode;
    @FXML
    private TableColumn<Branch, String> colName;
    @FXML
    private TableColumn<Branch, String> colContact;
    @FXML
    private TableColumn<Branch, String> colAddress;

    @FXML
    private TextField txtCode;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtContact;
    @FXML
    private TextArea txtAddress;

    private final BranchService branchService = new BranchService();
    private Branch selectedBranch;

    @FXML
    public void initialize() {
        //Role check
        if (Session.getRole() != UserRole.ADMIN) {
            new Alert(Alert.AlertType.ERROR, "Access denied! Admin only.").showAndWait();
            tblBranches.setDisable(true);
            return;
        }

        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        loadBranches();

        tblBranches.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldVal, newVal) -> {
                    selectedBranch = newVal;
                    if (newVal != null) {
                        txtCode.setText(newVal.getCode());
                        txtName.setText(newVal.getName());
                        txtContact.setText(newVal.getContact());
                        txtAddress.setText(newVal.getAddress());
                    }
                });
    }

    private void loadBranches() {
        try {
            tblBranches.setItems(
                    FXCollections.observableArrayList(
                            branchService.list()
                    )
            );
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    @FXML
    void saveBranch() {
        try {
            Branch b = new Branch();
            b.setCode(txtCode.getText());
            b.setName(txtName.getText());
            b.setAddress(txtAddress.getText());
            b.setContact(txtContact.getText());

            branchService.create(b);
            loadBranches();
            clearForm();

            showInfo("Branch saved successfully");

        } catch (Exception e) {
            showError(e.getMessage());
        }
    }
    
     @FXML
    void updateBranch() {

        if (selectedBranch == null) {
            showError("Please select a branch to update");
            return;
        }

        try {
            selectedBranch.setCode(txtCode.getText());
            selectedBranch.setName(txtName.getText());
            selectedBranch.setAddress(txtAddress.getText());
            selectedBranch.setContact(txtContact.getText());

            branchService.update(selectedBranch);
            loadBranches();

            showInfo("Branch updated successfully");

        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    @FXML
    void deleteBranch() {

        if (selectedBranch == null) {
            showError("Please select a branch to delete");
            return;
        }

        Alert confirm = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Are you sure you want to delete this branch?",
                ButtonType.YES,
                ButtonType.NO
        );

        confirm.showAndWait().ifPresent(btn -> {
            if (btn == ButtonType.YES) {
                try {
                    branchService.delete(selectedBranch.getId());
                    loadBranches();
                    clearForm();
                    showInfo("Branch deleted successfully");
                } catch (Exception e) {
                    showError(e.getMessage());
                }
            }
        });
    }
    
    @FXML
    void clearForm() {
        txtCode.clear();
        txtName.clear();
        txtContact.clear();
        txtAddress.clear();
        selectedBranch = null;
        tblBranches.getSelectionModel().clearSelection();
    }

    // ---------- ALERT HELPERS ----------
    private void showError(String msg) {
        new Alert(Alert.AlertType.ERROR, msg).showAndWait();
    }

    private void showInfo(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).showAndWait();
    }
}
