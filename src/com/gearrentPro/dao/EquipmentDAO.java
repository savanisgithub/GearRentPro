/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gearrentPro.dao;

import com.gearrentPro.db.DBConnection;
import com.gearrentPro.entity.Equipment;
import com.gearrentPro.entity.EquipmentStatus;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class EquipmentDAO {
    public void save(Equipment e) throws Exception {

        String sql = "INSERT INTO equipment " +
                "(branch_id, category_id, brand, model, purchase_year, base_price, deposit, status) " +
                "VALUES (?,?,?,?,?,?,?,?)";

        Connection con = DBConnection.getInstance().getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, e.getBranchId());
        ps.setInt(2, e.getCategoryId());
        ps.setString(3, e.getBrand());
        ps.setString(4, e.getModel());
        ps.setInt(5, e.getPurchaseYear());
        ps.setDouble(6, e.getBasePrice());
        ps.setDouble(7, e.getDeposit());
        ps.setString(8, e.getStatus().name());

        ps.executeUpdate();
    }

    public void update(Equipment e) throws Exception {

        String sql = "UPDATE equipment SET " +
                "category_id=?, brand=?, model=?, purchase_year=?, base_price=?, deposit=?, status=? " +
                "WHERE id=?";

        Connection con = DBConnection.getInstance().getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, e.getCategoryId());
        ps.setString(2, e.getBrand());
        ps.setString(3, e.getModel());
        ps.setInt(4, e.getPurchaseYear());
        ps.setDouble(5, e.getBasePrice());
        ps.setDouble(6, e.getDeposit());
        ps.setString(7, e.getStatus().name());
        ps.setInt(8, e.getId());

        ps.executeUpdate();
    }

    public void delete(int id) throws Exception {

        String sql = "DELETE FROM equipment WHERE id=?";
        Connection con = DBConnection.getInstance().getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, id);
        ps.executeUpdate();
    }

    public List<Equipment> findByBranch(int branchId) throws Exception {

        String sql = "SELECT * FROM equipment WHERE branch_id=?";
        Connection con = DBConnection.getInstance().getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, branchId);

        ResultSet rs = ps.executeQuery();
        List<Equipment> list = new ArrayList<>();

        while (rs.next()) {
            list.add(map(rs));
        }
        return list;
    }

    public List<Equipment> search(
            int branchId,
            Integer categoryId,
            EquipmentStatus status,
            String keyword
    ) throws Exception {

        StringBuilder sql = new StringBuilder(
                "SELECT * FROM equipment WHERE branch_id=?"
        );

        if (categoryId != null) sql.append(" AND category_id=").append(categoryId);
        if (status != null) sql.append(" AND status='").append(status.name()).append("'");
        if (keyword != null && !keyword.isBlank())
            sql.append(" AND (brand LIKE '%").append(keyword)
               .append("%' OR model LIKE '%").append(keyword).append("%')");

        Connection con = DBConnection.getInstance().getConnection();
        PreparedStatement ps = con.prepareStatement(sql.toString());
        ps.setInt(1, branchId);

        ResultSet rs = ps.executeQuery();
        List<Equipment> list = new ArrayList<>();

        while (rs.next()) {
            list.add(map(rs));
        }
        return list;
    }

    private Equipment map(ResultSet rs) throws Exception {
        Equipment e = new Equipment();
        e.setId(rs.getInt("id"));
        e.setBranchId(rs.getInt("branch_id"));
        e.setCategoryId(rs.getInt("category_id"));
        e.setBrand(rs.getString("brand"));
        e.setModel(rs.getString("model"));
        e.setPurchaseYear(rs.getInt("purchase_year"));
        e.setBasePrice(rs.getDouble("base_price"));
        e.setDeposit(rs.getDouble("deposit"));
        e.setStatus(EquipmentStatus.valueOf(rs.getString("status")));
        return e;
    }
}
