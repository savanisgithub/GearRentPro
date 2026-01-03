/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gearrentPro.controller;

import com.gearrentPro.auth.Session;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import com.gearrentPro.entity.Category;
import com.gearrentPro.entity.UserRole;
import com.gearrentPro.service.CategoryService;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author User
 */
public class CategoryController {

    @FXML
    private TableView<Category> tblCategories;
    @FXML
    private TableColumn<Category, String> colName;
    @FXML
    private TableColumn<Category, String> colDescription;
    @FXML
    private TableColumn<Category, String> colPrice;
    @FXML
    private TableColumn<Category, Double> colWeekend;
    @FXML
    private TableColumn<Category, Double> colLateFee;
    @FXML
    private TableColumn<Category, Boolean> colActive;

    @FXML
    private TextField txtName;
    @FXML
    private TextArea txtDescription;
    @FXML
    private TextField txtPriceFactor;
    @FXML
    private TextField txtWeekendMultiplier;
    @FXML
    private TextField txtLateFee;

    private final CategoryService categoryService = new CategoryService();
    private Category selected;

    @FXML
    public void initialize() {

        if (!(Session.isAdmin() || Session.isBranchManager())) {
            new Alert(Alert.AlertType.ERROR,
                    "Access denied!\nAdmin or Branch Manager only.")
                    .showAndWait();
            tblCategories.setDisable(true);
            return;
        }

        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("priceFactor"));
        colWeekend.setCellValueFactory(new PropertyValueFactory<>("weekendMultiplier"));
        colLateFee.setCellValueFactory(new PropertyValueFactory<>("lateFee"));
        colActive.setCellValueFactory(new PropertyValueFactory<>("active"));

        loadTable();

        tblCategories.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, o, n) -> {
                    selected = n;
                    if (n != null) {
                        txtName.setText(n.getName());
                        txtDescription.setText(n.getDescription());
                        txtPriceFactor.setText(String.valueOf(n.getPriceFactor()));
                        txtWeekendMultiplier.setText(String.valueOf(n.getWeekendMultiplier()));
                        txtLateFee.setText(n.getLateFee() == null ? "" : String.valueOf(n.getLateFee()));
                    }
                });
    }

    private void loadTable() {
        try {
            tblCategories.setItems(
                    FXCollections.observableArrayList(categoryService.list()));
        } catch (Exception e) {
            error(e.getMessage());
        }
    }

    @FXML
    void create() {
        try {
            Category c = buildFromForm();
            c.setActive(true);
            categoryService.create(c);
            loadTable();
            clear();
            showInfo("Category saved successfully");
        } catch (Exception e) {
            error(e.getMessage());
        }
    }

    @FXML
    void update() {
        if (selected == null) {
            error("Please select a category to update");
            return;
        }
        try {
            Category c = buildFromForm();
            c.setId(selected.getId());
            c.setActive(selected.isActive());
            categoryService.update(c);
            loadTable();
            showInfo("Category updated successfully");
            clear();
        } catch (Exception e) {
            error(e.getMessage());
        }
    }

    @FXML
    void delete() {
        if (selected == null) {
            error("Please select a category to delete");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure you want to delete this category?",
                ButtonType.YES, ButtonType.NO);

        confirm.showAndWait().ifPresent(btn -> {
            if (btn == ButtonType.YES) {
                try {
                    categoryService.delete(selected.getId());
                    loadTable();
                    clear();
                    showInfo("Category deleted successfully");
                } catch (Exception e) {
                    error(e.getMessage());
                }
            }
        });
    }

    @FXML
    void activate() {
        if (selected == null) {
            error("Please select a category to activate");
            return;
        }
        try {
            categoryService.setActive(selected.getId(), true);
            loadTable();
            showInfo("Category activated");
            clear();
        } catch (Exception e) {
            error(e.getMessage());
        }
    }

    @FXML
    void deactivate() {
        if (selected == null) {
            error("Please select a category to deactivate");
            return;
        }
        try {
            categoryService.setActive(selected.getId(), false);
            loadTable();
            showInfo("Category deactivated");
            clear();
        } catch (Exception e) {
            error(e.getMessage());
        }
    }

    private Category buildFromForm() {
        Category c = new Category();
        c.setName(safeTrim(txtName.getText()));
        c.setDescription(safeTrim(txtDescription.getText()));

        c.setPriceFactor(parseDouble(txtPriceFactor.getText(), "Base price factor is required"));
        c.setWeekendMultiplier(parseDouble(txtWeekendMultiplier.getText(), "Weekend multiplier is required"));

        String lateFeeText = safeTrim(txtLateFee.getText());
        if (lateFeeText.isEmpty()) {
            c.setLateFee(null);
        } else {
            c.setLateFee(parseDouble(lateFeeText, "Late fee must be a number"));
        }

        return c;
    }

    private double parseDouble(String val, String errorMsg) {
        try {
            if (val == null || val.trim().isEmpty()) {
                throw new NumberFormatException();
            }
            return Double.parseDouble(val.trim());
        } catch (Exception e) {
            throw new IllegalArgumentException(errorMsg);
        }
    }

    private String safeTrim(String s) {
        return s == null ? "" : s.trim();
    }

    @FXML
    void clear() {
        txtName.clear();
        txtDescription.clear();
        txtPriceFactor.clear();
        txtWeekendMultiplier.clear();
        txtLateFee.clear();
        selected = null;
        tblCategories.getSelectionModel().clearSelection();
    }

    private void error(String msg) {
        new Alert(Alert.AlertType.ERROR, msg).showAndWait();
    }

    private void showInfo(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).showAndWait();
    }
}
