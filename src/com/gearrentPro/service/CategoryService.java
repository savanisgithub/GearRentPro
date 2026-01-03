/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gearrentPro.service;

import com.gearrentPro.auth.Session;
import com.gearrentPro.dao.CategoryDAO;
import com.gearrentPro.entity.Category;
import com.gearrentPro.entity.UserRole;
import java.util.List;

/**
 *
 * @author User
 */
public class CategoryService {

    private final CategoryDAO dao = new CategoryDAO();
    
     private void roleCheck() {
        // Admin OR Branch Manager only
        if (!(Session.isAdmin() || Session.isBranchManager())) {
            throw new SecurityException("Access denied. Admin or Branch Manager only.");
        }
    }

    public void create(Category c) throws Exception {
        roleCheck();
        validate(c);
        dao.create(c);
    }

    public void update(Category c) throws Exception {
        roleCheck();
        if (c.getId() <= 0) {
            throw new IllegalArgumentException("Invalid category");
        }
        validate(c);
        dao.update(c);
    }
    
     public void delete(int id) throws Exception {
        roleCheck();
        dao.delete(id);
    }
     
     public void setActive(int id, boolean active) throws Exception {
        roleCheck();
        dao.setActive(id, active);
    }

    public List<Category> list() throws Exception {
        roleCheck();
        return dao.findAll();
    }

    private void validate(Category c) {
        if (c.getName() == null || c.getName().isBlank()) {
            throw new IllegalArgumentException("Category name is required");
        }
        if (c.getDescription() == null || c.getDescription().isBlank()) {
            throw new IllegalArgumentException("Description is required");
        }

        if (c.getPriceFactor() <= 0) {
            throw new IllegalArgumentException("Base price factor must be > 0");
        }
        if (c.getWeekendMultiplier() <= 0) {
            throw new IllegalArgumentException("Weekend multiplier must be > 0");
        }

        if (c.getPriceFactor() > 10) {
            throw new IllegalArgumentException("Price factor is out of valid range");
        }
        if (c.getWeekendMultiplier() > 5) {
            throw new IllegalArgumentException("Weekend multiplier is out of valid range");
        }

        if (c.getLateFee() != null && c.getLateFee() < 0) {
            throw new IllegalArgumentException("Late fee cannot be negative");
        }
    }

}
