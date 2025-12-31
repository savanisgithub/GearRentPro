/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gearrentPro.service;

import com.gearrentPro.dao.UserDAO;
import com.gearrentPro.entity.SystemUser;

/**
 *
 * @author User
 */
public class AuthService {
      private final UserDAO userDAO = new UserDAO();

    public SystemUser login(String username, String password) throws Exception {
        if (username == null || username.isBlank()) throw new IllegalArgumentException("Username is required");
        if (password == null || password.isBlank()) throw new IllegalArgumentException("Password is required");

        SystemUser user = userDAO.findByUsernameAndPassword(username.trim(), password);
        if (user == null) throw new IllegalArgumentException("Invalid username or password");
        return user;
    }
}
