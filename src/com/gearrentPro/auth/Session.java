/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gearrentPro.auth;

import com.gearrentPro.entity.UserRole;

/**
 *
 * @author User
 */
public class Session {
    private static String username;
    private static UserRole role;

    // 🔹 STORE LOGGED USER DATA (NO EXCEPTIONS HERE)
    public static void set(String u, UserRole r) {
        username = u;
        role = r;
    }

    public static String getUsername() {
        return username;
    }

    public static UserRole getRole() {
        return role;
    }

    public static boolean isAdmin() {
        return role == UserRole.ADMIN;
    }

    public static boolean isBranchManager() {
        return role == UserRole.BRANCH_MANAGER;
    }

    public static boolean isStaff() {
        return role == UserRole.STAFF;
    }

    public static void clear() {
        username = null;
        role = null;
    }

}
