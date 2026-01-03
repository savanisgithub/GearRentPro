/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gearrentPro.dao;

import com.gearrentPro.db.DBConnection;
import com.gearrentPro.entity.SystemUser;
import com.gearrentPro.entity.UserRole;

import java.sql.*;


/**
 *
 * @author User
 */
public class UserDAO {
     public SystemUser findByUsernameAndPassword(String username, String password) throws Exception {

        String sql =
        "SELECT * FROM system_user " +
        "WHERE username=? AND password=? AND is_active=1";

        Connection con = DBConnection.getInstance().getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, username);
        ps.setString(2, password);

        ResultSet rs = ps.executeQuery();

        if (!rs.next()) return null;

        SystemUser user = new SystemUser();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setRole(UserRole.valueOf(rs.getString("role")));

        int branchId = rs.getInt("branch_id");
        user.setBranchId(rs.wasNull() ? null : branchId);

        user.setActive(rs.getBoolean("is_active"));
        return user;
    }
}
