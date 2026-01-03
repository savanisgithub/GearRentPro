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

    private static Integer userId;
    private static String username;
    private static UserRole role;
    private static Integer branchId;

    // SET SESSION AFTER LOGIN
    public static void set(int uid, String uname, UserRole r, Integer bId) {
        userId = uid;
        username = uname;
        role = r;
        branchId = bId;
    }

    public static int getUserId() {
        return userId;
    }

    public static String getUsername() {
        return username;
    }

    public static UserRole getRole() {
        return role;
    }

    public static Integer getBranchId() {
        return branchId;
    }

    // ---- ROLE HELPERS ----
    public static boolean isAdmin() {
        return role == UserRole.ADMIN;
    }

    public static boolean isBranchManager() {
        return role == UserRole.BRANCH_MANAGER;
    }

    public static boolean isStaff() {
        return role == UserRole.STAFF;
    }

    // ---- CLEAR SESSION ----
    public static void clear() {
        userId = null;
        username = null;
        role = null;
        branchId = null;
    }
}
