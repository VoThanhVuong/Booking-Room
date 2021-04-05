/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.UserDTO;
import UTILS.DBIUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author vuong
 */
public class UserDAO {

    public String convertDateToString(Date d) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(d);
    }

    public Date convertStringToDate(String s) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.parse(s);
    }

    public UserDTO checkLorgin(String userID, String password) throws SQLException {
        UserDTO result = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBIUtils.getConnnection();
            if (conn != null) {
                String sql = "select UserName, RoleID from tblUser "
                        + " where userID=? and password=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String userName = rs.getString("UserName");
                    String roleID = rs.getString("RoleID");
                    result = new UserDTO(userID, userName, "", "", "", "", null, null, "", "", null, roleID);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }
    
    public UserDTO checkLorgin(String userID) throws SQLException {
        UserDTO result = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBIUtils.getConnnection();
            if (conn != null) {
                String sql = "select UserName, RoleID, Email from tblUser "
                        + " where userID=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String userName = rs.getString("Email");
                    result = new UserDTO(userID, userName, "", "", "", "", null, null, "", "", null, "");
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }

    public void createUser(UserDTO user) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBIUtils.getConnnection();
            if (conn != null) {
                String sql = "Insert into tblUser (userID, userName, password, phone, email, address, birthday, dateOfCreate, numberIDCard, numberAccountBank, availability, roleID)"
                        + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, user.getUserID());
                stm.setString(2, user.getUserName());
                stm.setString(3, user.getPassword());
                stm.setString(4, user.getPhone());
                stm.setString(5, user.getEmail());
                stm.setString(6, user.getAddress());
                stm.setString(7, convertDateToString(user.getBirthday()));
                stm.setString(8, convertDateToString(user.getDateOfCreate()));
                stm.setString(9, user.getNumberIDCard());
                stm.setString(10, user.getNumberAccountBank());
                stm.setString(11, user.getAvailability().toString());
                stm.setString(12, user.getRoleID());
                stm.executeUpdate();
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
    public void createUser(String userID, Date date, String email) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBIUtils.getConnnection();
            if (conn != null) {
                String sql = "Insert into tblUser (userID, email, dateOfCreate, availability, roleID)"
                        + " VALUES(?,?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);
                stm.setString(2, email);
                stm.setString(3, convertDateToString(date));
                stm.setBoolean(4, true);
                stm.setString(5, "US");
                stm.executeUpdate();
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
}
