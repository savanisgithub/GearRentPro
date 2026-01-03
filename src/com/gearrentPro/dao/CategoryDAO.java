/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gearrentPro.dao;

import com.gearrentPro.db.DBConnection;
import com.gearrentPro.entity.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class CategoryDAO {

    public void create(Category c) throws Exception {

        String sql = "INSERT INTO category(name, description, price_factor, weekend_multiplier, late_fee, is_active) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        Connection con = DBConnection.getInstance().getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, c.getName());
        ps.setString(2, c.getDescription());
        ps.setDouble(3, c.getPriceFactor());
        ps.setDouble(4, c.getWeekendMultiplier());

        if (c.getLateFee() == null) {
            ps.setNull(5, Types.DOUBLE);
        } else {
            ps.setDouble(5, c.getLateFee());
        }

        ps.setBoolean(6, c.isActive());
        ps.executeUpdate();
    }

    public List<Category> findAll() throws Exception {
        String sql = "SELECT * FROM category ORDER BY name";
        Connection con = DBConnection.getInstance().getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();
        List<Category> list = new ArrayList<>();

        while (rs.next()) {
            Category c = new Category();
            c.setId(rs.getInt("id"));
            c.setName(rs.getString("name"));
            c.setDescription(rs.getString("description"));
            c.setPriceFactor(rs.getDouble("price_factor"));
            c.setWeekendMultiplier(rs.getDouble("weekend_multiplier"));

            double lateFee = rs.getDouble("late_fee");
            c.setLateFee(rs.wasNull() ? null : lateFee);

            c.setActive(rs.getBoolean("is_active"));
            list.add(c);
        }
        return list;
    }

    public void update(Category c) throws Exception {

        String sql = "UPDATE category SET name=?, description=?, price_factor=?, weekend_multiplier=?, late_fee=?, is_active=? "
                + "WHERE id=?";

        Connection con = DBConnection.getInstance().getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, c.getName());
        ps.setString(2, c.getDescription());
        ps.setDouble(3, c.getPriceFactor());
        ps.setDouble(4, c.getWeekendMultiplier());

        if (c.getLateFee() == null) {
            ps.setNull(5, Types.DOUBLE);
        } else {
            ps.setDouble(5, c.getLateFee());
        }

        ps.setBoolean(6, c.isActive());
        ps.setInt(7, c.getId());

        ps.executeUpdate();
    }

    public void delete(int id) throws Exception {
        String sql = "DELETE FROM category WHERE id=?";
        Connection con = DBConnection.getInstance().getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    public void setActive(int id, boolean active) throws Exception {

        String sql = "UPDATE category SET is_active=? WHERE id=?";
        Connection con = DBConnection.getInstance().getConnection();

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setBoolean(1, active);
        ps.setInt(2, id);
        ps.executeUpdate();
    }
}
