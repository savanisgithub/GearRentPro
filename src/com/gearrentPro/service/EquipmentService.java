/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gearrentPro.service;

import com.gearrentPro.auth.Session;
import com.gearrentPro.dao.EquipmentDAO;
import com.gearrentPro.entity.Equipment;

/**
 *
 * @author User
 */
import com.gearrentPro.entity.EquipmentStatus;
import java.time.Year;
import java.util.List;
public class EquipmentService {
     private final EquipmentDAO dao = new EquipmentDAO();

    private void roleCheck() {
        // Admin can see all later, but for now:
        if (!(Session.isAdmin() || Session.isBranchManager() || Session.isStaff())) {
            throw new SecurityException("Access denied");
        }
    }

    public void create(Equipment e) throws Exception {
        roleCheck();

        // Branch restriction (Manager & Staff)
        if (!Session.isAdmin() && e.getBranchId() != Session.getBranchId()) {
            throw new SecurityException("Cannot manage equipment of another branch");
        }

        validate(e);
        dao.save(e);
    }

    public void update(Equipment e) throws Exception {
        roleCheck();
        validate(e);
        dao.update(e);
    }

    public void delete(int id) throws Exception {
        roleCheck();
        dao.delete(id);
    }

    public List<Equipment> listByBranch(int branchId) throws Exception {
        roleCheck();

        if (!Session.isAdmin() && branchId != Session.getBranchId())
            throw new SecurityException("Access denied");

        return dao.findByBranch(branchId);
    }

    public List<Equipment> search(
            int branchId,
            Integer categoryId,
            EquipmentStatus status,
            String keyword
    ) throws Exception {

        roleCheck();

        if (!Session.isAdmin() && branchId != Session.getBranchId())
            throw new SecurityException("Access denied");

        return dao.search(branchId, categoryId, status, keyword);
    }

    private void validate(Equipment e) {

        if (e.getBrand() == null || e.getBrand().isBlank())
            throw new IllegalArgumentException("Brand is required");

        if (e.getModel() == null || e.getModel().isBlank())
            throw new IllegalArgumentException("Model is required");

        int year = Year.now().getValue();
        if (e.getPurchaseYear() < 2000 || e.getPurchaseYear() > year)
            throw new IllegalArgumentException("Invalid purchase year");

        if (e.getBasePrice() <= 0)
            throw new IllegalArgumentException("Base price must be greater than 0");

        if (e.getDeposit() < 0)
            throw new IllegalArgumentException("Deposit cannot be negative");

        if (e.getStatus() == null)
            throw new IllegalArgumentException("Status is required");
    }
}
