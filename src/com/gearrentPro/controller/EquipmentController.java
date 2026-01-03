/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gearrentPro.controller;

import com.gearrentPro.auth.Session;
import com.gearrentPro.entity.EquipmentStatus;
import com.gearrentPro.entity.Equipment;
import com.gearrentPro.service.EquipmentService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author User
 */
public class EquipmentController {

    @FXML
    private TableView<Equipment> tblEquipment;
    @FXML
    private TableColumn<Equipment, String> colBrand;
    @FXML
    private TableColumn<Equipment, String> colModel;
    @FXML
    private TableColumn<Equipment, EquipmentStatus> colStatus;

    @FXML
    private TextField txtBrand;
    @FXML
    private TextField txtModel;
    @FXML
    private TextField txtYear;
    @FXML
    private TextField txtPrice;
    @FXML
    private TextField txtDeposit;
    @FXML
    private TextField txtSearch;

    @FXML
    private ComboBox<Integer> cmbCategory;
    @FXML
    private ComboBox<EquipmentStatus> cmbStatus;

    @FXML
    private Button btnSave;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;

    private final EquipmentService equipmentService = new EquipmentService();
    private Equipment selected;

    @FXML
    public void initialize() {
        // 🔐 ROLE CHECK (only Admin, Manager, Staff)
        if (!(Session.isAdmin() || Session.isBranchManager() || Session.isStaff())) {
            new Alert(Alert.AlertType.ERROR, "Access denied").showAndWait();
            tblEquipment.setDisable(true);
            return;
        }

        // STAFF → view only
        if (Session.isStaff()) {
            btnSave.setDisable(true);
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);

            txtBrand.setDisable(true);
            txtModel.setDisable(true);
            txtYear.setDisable(true);
            txtPrice.setDisable(true);
            txtDeposit.setDisable(true);
            cmbCategory.setDisable(true);
            cmbStatus.setDisable(true);
        }

        colBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        cmbStatus.setItems(FXCollections.observableArrayList(EquipmentStatus.values()));

        loadTable();

        tblEquipment.getSelectionModel().selectedItemProperty().addListener((obs, o, n) -> {
            selected = n;
            if (n != null) {
                txtBrand.setText(n.getBrand());
                txtModel.setText(n.getModel());
                txtYear.setText(String.valueOf(n.getPurchaseYear()));
                txtPrice.setText(String.valueOf(n.getBasePrice()));
                txtDeposit.setText(String.valueOf(n.getDeposit()));
                cmbStatus.setValue(n.getStatus());
                cmbCategory.setValue(n.getCategoryId());
            }
        });
    }

    private void loadTable() {
        try {
            if (Session.isAdmin()) {
                // Admin: show all equipment later (for now, prevent crash)
                showError("Admin view for equipment is not branch-limited yet");
                return;
            }

            Integer branchId = Session.getBranchId();

            if (branchId == null) {
                showError("Branch is not assigned to this user");
                return;
            }

            tblEquipment.setItems(FXCollections.observableArrayList(
                    equipmentService.listByBranch(branchId)
            ));

        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    @FXML
    void save() {
        try {
            Equipment e = buildFromForm();
            equipmentService.create(e);
            loadTable();
            clear();
            showInfo("Equipment saved");
        } catch (Exception ex) {
            showError(ex.getMessage());
        }
    }

    @FXML
    void update() {
        if (selected == null) {
            return;
        }
        try {
            Equipment e = buildFromForm();
            e.setId(selected.getId());
            equipmentService.update(e);
            loadTable();
            showInfo("Equipment updated");
        } catch (Exception ex) {
            showError(ex.getMessage());
        }
    }

    @FXML
    void delete() {
        if (selected == null) {
            return;
        }
        try {
            equipmentService.delete(selected.getId());
            loadTable();
            clear();
            showInfo("Equipment deleted");
        } catch (Exception ex) {
            showError(ex.getMessage());
        }
    }

    @FXML
    void search() {
        try {
            tblEquipment.setItems(FXCollections.observableArrayList(
                    equipmentService.search(
                            Session.getBranchId(),
                            cmbCategory.getValue(),
                            cmbStatus.getValue(),
                            txtSearch.getText()
                    )
            ));
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    private Equipment buildFromForm() {
        Equipment e = new Equipment();
        e.setBranchId(Session.getBranchId());
        e.setCategoryId(cmbCategory.getValue());
        e.setBrand(txtBrand.getText());
        e.setModel(txtModel.getText());
        e.setPurchaseYear(Integer.parseInt(txtYear.getText()));
        e.setBasePrice(Double.parseDouble(txtPrice.getText()));
        e.setDeposit(Double.parseDouble(txtDeposit.getText()));
        e.setStatus(cmbStatus.getValue());
        return e;
    }

    @FXML
    void clear() {
        txtBrand.clear();
        txtModel.clear();
        txtYear.clear();
        txtPrice.clear();
        txtDeposit.clear();
        txtSearch.clear();
        cmbStatus.setValue(null);
        cmbCategory.setValue(null);
        selected = null;
    }

    private void showError(String msg) {
        new Alert(Alert.AlertType.ERROR, msg).showAndWait();
    }

    private void showInfo(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).showAndWait();
    }
}
