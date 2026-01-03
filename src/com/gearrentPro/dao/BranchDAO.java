/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gearrentPro.dao;

import com.gearrentPro.db.DBConnection;
import com.gearrentPro.entity.Branch;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class BranchDAO {

    public void save(Branch branch) throws Exception {

        String sql = "INSERT INTO branch(code, name, address, contact) VALUES (?, ?, ?, ?)";
        Connection con = DBConnection.getInstance().getConnection();

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, branch.getCode());
        ps.setString(2, branch.getName());
        ps.setString(3, branch.getAddress());
        ps.setString(4, branch.getContact());

        ps.executeUpdate();
    }

    public List<Branch> findAll() throws Exception {

        String sql = "SELECT * FROM branch";
        Connection con = DBConnection.getInstance().getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();
        List<Branch> list = new ArrayList<>();

        while (rs.next()) {
            Branch b = new Branch();
            b.setId(rs.getInt("id"));
            b.setCode(rs.getString("code"));
            b.setName(rs.getString("name"));
            b.setAddress(rs.getString("address"));
            b.setContact(rs.getString("contact"));
            list.add(b);
        }
        return list;
    }

    public void update(Branch branch) throws Exception {

        String sql = "UPDATE branch SET code=?, name=?, address=?, contact=? WHERE id=?";
        Connection con = DBConnection.getInstance().getConnection();

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, branch.getCode());
        ps.setString(2, branch.getName());
        ps.setString(3, branch.getAddress());
        ps.setString(4, branch.getContact());
        ps.setInt(5, branch.getId());

        ps.executeUpdate();
    }

    public void delete(int id) throws Exception {

        String sql = "DELETE FROM branch WHERE id=?";
        Connection con = DBConnection.getInstance().getConnection();

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    public void create(Branch b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
