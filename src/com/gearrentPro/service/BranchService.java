/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gearrentPro.service;

import com.gearrentPro.auth.Session;
import com.gearrentPro.dao.BranchDAO;
import com.gearrentPro.entity.Branch;

import java.util.List;

/**
 *
 * @author User
 */
public class BranchService {
    private final BranchDAO dao = new BranchDAO();

    public void create(Branch b) throws Exception {
        if (!Session.isAdmin()) throw new SecurityException("Access denied. Admin only.");

        validate(b);
        dao.save(b);
    }

    public void update(Branch b) throws Exception {
        if (!Session.isAdmin()) throw new SecurityException("Access denied. Admin only.");

        if (b.getId() <= 0) throw new IllegalArgumentException("Invalid branch");
        validate(b);
        dao.update(b);
    }

    public void delete(int id) throws Exception {
        if (!Session.isAdmin()) throw new SecurityException("Access denied. Admin only.");
        dao.delete(id);
    }

    public List<Branch> list() throws Exception {
        // Admin can see all branches
        if (!Session.isAdmin()) throw new SecurityException("Access denied. Admin only.");
        return dao.findAll();
    }

    private void validate(Branch b) {
        if (b.getCode() == null || b.getCode().isBlank()) throw new IllegalArgumentException("Branch code is required");
        if (b.getName() == null || b.getName().isBlank()) throw new IllegalArgumentException("Branch name is required");
        if (b.getAddress() == null || b.getAddress().isBlank()) throw new IllegalArgumentException("Address is required");
        if (b.getContact() == null || b.getContact().isBlank()) throw new IllegalArgumentException("Contact is required");
        if (b.getCode().length() > 10) throw new IllegalArgumentException("Branch code max length is 10");
    }
}
